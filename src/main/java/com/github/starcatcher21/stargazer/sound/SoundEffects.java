package com.github.starcatcher21.stargazer.sound;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class SoundEffects {
    public static final SoundEvent BLOCK_COSMIC_BREAK = register("block.cosmic_break");
    public static final SoundEvent BLOCK_COSMIC_PLACE = register("block.cosmic_place");
    public static final SoundEvent BLOCK_COSMIC_LAND = register("block.cosmic_land");
    public static final SoundEvent OST_FROM_THE_STARS = register("ost.from_the_stars");
    public static final SoundEvent OST_BALLAD_OF_THE_STARS = register("ost.ballad_of_the_stars");
    public static final SoundEvent OST_ADVENTURE_OF_THE_MOON = register("ost.adventure_of_the_moon");
    public static final SoundEvent COSMIC_MUSIC = register("cosmic.music");

    private static Holder<SoundEvent> register(Identifier id, Identifier soundId, float distanceToTravel) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createFixedRangeEvent(soundId, distanceToTravel));
    }

    private static SoundEvent register(String id) {
        return register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id));
    }

    private static SoundEvent register(Identifier id) {
        return register(id, id);
    }

    private static Holder.Reference<SoundEvent> registerReference(String id) {
        return registerReference(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID,id));
    }

    private static Holder.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId));
    }

    private static Holder.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId));
    }
    public static void init() {
    }
}
