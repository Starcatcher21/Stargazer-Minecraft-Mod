package com.github.starcatcher21.stargazer.particle;

import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class ParticlesClient {
    private ParticlesClient() {
    }

    public static void init() {
        // Use .get() if your Particles entries are RegistrySuppliers
        ParticleProviderRegistry.register(Particles.YELLOW_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.register(Particles.RED_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.register(Particles.BLUE_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.register(Particles.PURPLE_STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.register(Particles.STAR, StarParticle2.Factory::new);
        ParticleProviderRegistry.register(Particles.TINTED_STAR, StarParticle.TintedLeavesFactory::new);
    }
}