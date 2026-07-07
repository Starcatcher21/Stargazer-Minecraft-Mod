package com.github.starcatcher21.stargazer.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class StarParticle2 extends SingleQuadParticle {
    StarParticle2(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, boolean signal, TextureAtlasSprite sprite) {
        super(world, x, y, z, sprite);
        this.setAlpha(1.0f);
        this.setSize(0.05F, 0.05F);
        if (signal) {
            this.lifetime = this.random.nextInt(50) + 140;
        } else {
            this.lifetime = this.random.nextInt(50) + 40;
        }

        this.gravity = 3.0E-6F;
        this.xd = velocityX;
        this.yd = velocityY + this.random.nextFloat() / 500.0F;
        this.zd = velocityZ;
    }

public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }

        } else {
            this.remove();
        }
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(
                SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random
        ) {
            StarParticle2 campfireSmokeParticle = new StarParticle2(clientWorld, d, e, f, g, h, i, false, this.spriteProvider.get(random));
            return campfireSmokeParticle;
        }
    }
}
