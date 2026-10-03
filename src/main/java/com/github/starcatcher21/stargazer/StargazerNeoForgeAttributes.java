//? if neoforge {
/*package com.github.starcatcher21.stargazer;

import net.minecraft.world.entity.EntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber(modid = Stargazer.MOD_ID)
public class StargazerNeoForgeAttributes {

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        if (!event.has(EntityTypes.PLAYER, StargazerAttributes.DASH_LEVEL)) {
            event.add(EntityTypes.PLAYER, StargazerAttributes.DASH_LEVEL, 0.0);
        }
    }
}
*///? }
