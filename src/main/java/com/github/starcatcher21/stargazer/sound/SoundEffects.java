package com.github.starcatcher21.stargazer.sound;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundEffects {
    public static final SoundEvent BLOCK_COSMIC_BREAK = register("block.cosmic_break");
    public static final SoundEvent BLOCK_COSMIC_PLACE = register("block.cosmic_place");
    public static final SoundEvent BLOCK_COSMIC_LAND = register("block.cosmic_land");
    public static final SoundEvent OST_FROM_THE_STARS = register("ost.from_the_stars");
    public static final SoundEvent OST_BALLAD_OF_THE_STARS = register("ost.ballad_of_the_stars");
    public static final SoundEvent OST_ADVENTURE_OF_THE_MOON = register("ost.adventure_of_the_moon");

    private static RegistryEntry<SoundEvent> register(Identifier id, Identifier soundId, float distanceToTravel) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId, distanceToTravel));
    }

    private static SoundEvent register(String id) {
        return register(Identifier.of(Stargazer.MOD_ID, id));
    }

    private static SoundEvent register(Identifier id) {
        return register(id, id);
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        return registerReference(Identifier.of(Stargazer.MOD_ID,id));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }
}
