package com.github.starcatcher21.stargazer.particle;

import com.github.starcatcher21.stargazer.Stargazer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
    public static final ParticleType<TintedParticleEffect> TINTED_STAR = FabricParticleTypes.complex(TintedParticleEffect::createCodec, TintedParticleEffect::createPacketCodec);

    private static SimpleParticleType register(String name, boolean alwaysShow) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, name), FabricParticleTypes.simple(alwaysShow));
    }

    public static void init() {
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "yellow_star"), YELLOW_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "red_star"), RED_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "blue_star"), BLUE_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "purple_star"), PURPLE_STAR);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "star"), STAR);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Stargazer.MOD_ID, "tinted_star"), TINTED_STAR);
    }


    public static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(YELLOW_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(RED_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BLUE_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(PURPLE_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(TINTED_STAR, StarParticle.TintedLeavesFactory::new);
    }
}
