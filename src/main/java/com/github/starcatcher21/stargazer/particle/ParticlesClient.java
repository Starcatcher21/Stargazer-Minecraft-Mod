package com.github.starcatcher21.stargazer.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

@Environment(EnvType.CLIENT)
public final class ParticlesClient {
    private ParticlesClient() {
    }

    public static void init() {
        ParticleProviderRegistry.getInstance().register(Particles.YELLOW_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.getInstance().register(Particles.RED_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.getInstance().register(Particles.BLUE_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.getInstance().register(Particles.PURPLE_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.getInstance().register(Particles.STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.getInstance().register(Particles.TINTED_STAR, StarParticle.TintedLeavesFactory::new);
    }
}
