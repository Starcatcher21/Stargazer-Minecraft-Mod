package com.github.starcatcher21.stargazer.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public final class ParticlesClient {
    private ParticlesClient() {
    }

    public static void init() {
        ParticleFactoryRegistry.getInstance().register(Particles.YELLOW_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Particles.RED_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Particles.BLUE_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Particles.PURPLE_STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Particles.STAR, StarParticle2.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Particles.TINTED_STAR, StarParticle.TintedLeavesFactory::new);
    }
}
