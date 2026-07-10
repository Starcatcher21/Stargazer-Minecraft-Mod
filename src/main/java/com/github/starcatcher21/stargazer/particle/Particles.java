package com.github.starcatcher21.stargazer.particle;

import com.github.starcatcher21.stargazer.Stargazer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class Particles {
//    public static final ParticleType<SimpleParticleType> YELLOW_STAR = FabricParticleTypes.simple();
//    public static final ParticleType<SimpleParticleType> RED_STAR = FabricParticleTypes.simple();
//    public static final ParticleType<SimpleParticleType> BLUE_STAR = FabricParticleTypes.simple();
//    public static final ParticleType<SimpleParticleType> PURPLE_STAR = FabricParticleTypes.simple();
//    public static final ParticleType<SimpleParticleType> STAR = FabricParticleTypes.simple();
    public static final SimpleParticleType YELLOW_STAR = register("yellow_star", false);
    public static final SimpleParticleType RED_STAR = register("red_star", false);
    public static final SimpleParticleType BLUE_STAR = register("blue_star", false);
    public static final SimpleParticleType PURPLE_STAR = register("purple_star", false);
    public static final SimpleParticleType STAR = register("star", false);
    public static final ParticleType<ColorParticleOption> TINTED_STAR = FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec);

    private static SimpleParticleType register(String name, boolean alwaysShow) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name), FabricParticleTypes.simple(alwaysShow));
    }

    public static void init() {
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "yellow_star"), YELLOW_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "red_star"), RED_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "blue_star"), BLUE_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "purple_star"), PURPLE_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "star"), STAR);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "tinted_star"), TINTED_STAR);
    }

}
