//? if neoforge {
package com.github.starcatcher21.stargazer;

import net.minecraft.world.entity.EntityType;
//? if >= 26.2 {
/*import net.minecraft.world.entity.EntityTypes;
*///? }
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

public class StargazerNeoForgeAttributes {

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        //? if >= 26.2 {
        /*if (!event.has(EntityTypes.PLAYER, StargazerAttributes.DASH_LEVEL)) {
            event.add(EntityTypes.PLAYER, StargazerAttributes.DASH_LEVEL, 0.0);
        }
        *///?} else {
        event.add(EntityType.PLAYER, StargazerAttributes.DASH_LEVEL, 0.0);
        //?}
    }
}
//? }
