//? if neoforge && < 26.2 {
package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.renderer.effects.CosmicSkyEffects;
import com.github.starcatcher21.stargazer.renderer.effects.RedOrbSkyEffects;
import com.github.starcatcher21.stargazer.renderer.effects.WanderSkyEffects;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

@EventBusSubscriber(modid = Stargazer.MOD_ID, value = Dist.CLIENT)
public class NeoforgeEffects {
    @SubscribeEvent
    public static void onRegisterEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic"), new CosmicSkyEffects());
        event.register(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb"), new RedOrbSkyEffects());
        event.register(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "wander"), new WanderSkyEffects());
    }
}
//? }
