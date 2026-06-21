package com.github.starcatcher21.stargazer.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class StarParticle2 extends BillboardParticle {
    StarParticle2(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, boolean signal, Sprite sprite) {
        super(world, x, y, z, sprite);
        this.scale(1.0F);
        this.setBoundingBoxSpacing(0.05F, 0.05F);
        if (signal) {
            this.maxAge = this.random.nextInt(50) + 140;
        } else {
            this.maxAge = this.random.nextInt(50) + 40;
        }

        this.gravityStrength = 3.0E-6F;
        this.velocityX = velocityX;
        this.velocityY = velocityY + this.random.nextFloat() / 500.0F;
        this.velocityZ = velocityZ;
    }

    @Override
    public void tick() {
        this.lastX = this.x;
        this.lastY = this.y;
        this.lastZ = this.z;
        if (this.age++ < this.maxAge && !(this.alpha <= 0.0F)) {
            this.velocityX = this.velocityX + this.random.nextFloat() / 5000.0F * (this.random.nextBoolean() ? 1 : -1);
            this.velocityZ = this.velocityZ + this.random.nextFloat() / 5000.0F * (this.random.nextBoolean() ? 1 : -1);
            this.velocityY = this.velocityY - this.gravityStrength;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (this.age >= this.maxAge - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }
        } else {
            this.markDead();
        }
    }

    @Override
    public BillboardParticle.RenderType getRenderType() {
        return BillboardParticle.RenderType.PARTICLE_ATLAS_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(
                SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random
        ) {
            StarParticle2 campfireSmokeParticle = new StarParticle2(clientWorld, d, e, f, g, h, i, false, this.spriteProvider.getSprite(random));
            return campfireSmokeParticle;
        }
    }
}
