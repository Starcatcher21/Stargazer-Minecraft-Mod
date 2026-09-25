package com.github.starcatcher21.stargazer.fabric;

import com.github.starcatcher21.stargazer.GameRules;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.energy.fabric.EnergyHooksImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.player.Player;

public final class StargazerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Stargazer.initCommon();
        GameRules.init();
        FabricPlacedFeatures.init();
        EnergyHooksImpl.registerEnergyStorages();
        FabricDefaultAttributeRegistry.register(
                EntityTypes.PLAYER,
                Player.createAttributes().add(StargazerAttributes.DASH_LEVEL, 0.0)
        );

    }
}
