package com.github.starcatcher21.stargazer.sound;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class SoundEffects {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> BLOCK_COSMIC_BREAK = register("block.cosmic_break");
    public static final RegistrySupplier<SoundEvent> BLOCK_COSMIC_PLACE = register("block.cosmic_place");
    public static final RegistrySupplier<SoundEvent> BLOCK_COSMIC_LAND = register("block.cosmic_land");
    public static final RegistrySupplier<SoundEvent> OST_FROM_THE_STARS = register("ost.from_the_stars");
    public static final RegistrySupplier<SoundEvent> OST_BALLAD_OF_THE_STARS = register("ost.ballad_of_the_stars");
    public static final RegistrySupplier<SoundEvent> OST_ADVENTURE_OF_THE_MOON = register("ost.adventure_of_the_moon");
    public static final RegistrySupplier<SoundEvent> COSMIC_MUSIC = register("cosmic.music");

    private static RegistrySupplier<SoundEvent> register(String path) {
        Identifier id =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        return SOUND_EVENTS.register(path, () -> SoundEvent.createVariableRangeEvent(id));
    }

    private static RegistrySupplier<SoundEvent> registerWithRange(String path, float distanceToTravel) {
        Identifier id =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        return SOUND_EVENTS.register(path, () -> SoundEvent.createFixedRangeEvent(id, distanceToTravel));
    }

    public static void init() {
        SOUND_EVENTS.register();
    }
}