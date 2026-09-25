package com.github.starcatcher21.stargazer.neoforge;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerClient;
import com.github.starcatcher21.stargazer.energy.neoforge.EnergyHooksImpl;
import com.github.starcatcher21.stargazer.neoforge.client.StargazerNeoForgeRenderEvents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Stargazer.MOD_ID)
public final class StargazerNeoforge {

    public StargazerNeoforge(IEventBus modEventBus, Dist dist) {
        modEventBus.addListener(this::commonSetup);


        RegistryKeysImpl.ENTITY_DATA_SERIALIZERS.register(modEventBus);

        Stargazer.initCommon();
        if (dist.isClient()) {
            StargazerClient.initClient();
            NeoForge.EVENT_BUS.register(StargazerNeoForgeRenderEvents.class);
        }
        modEventBus.register(StargazerNeoForgeAttributes.class);
        EnergyHooksImpl.init(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
}
