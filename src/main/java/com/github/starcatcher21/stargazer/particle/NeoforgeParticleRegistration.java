//? if neoforge {
package com.github.starcatcher21.stargazer.particle;

import com.github.starcatcher21.stargazer.Stargazer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = Stargazer.MOD_ID, value = Dist.CLIENT)
public class NeoforgeParticleRegistration {
    @SubscribeEvent
    public static void register(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(Particles.YELLOW_STAR.get(), StarParticle2.Factory::new);
        event.registerSpriteSet(Particles.BLUE_STAR.get(), StarParticle2.Factory::new);
        event.registerSpriteSet(Particles.RED_STAR.get(), StarParticle2.Factory::new);
        event.registerSpriteSet(Particles.PURPLE_STAR.get(), StarParticle2.Factory::new);
        event.registerSpriteSet(Particles.STAR.get(), StarParticle2.Factory::new);
        event.registerSpriteSet(Particles.TINTED_STAR.get(), StarParticle.TintedLeavesFactory::new);
    }
}
//? }
