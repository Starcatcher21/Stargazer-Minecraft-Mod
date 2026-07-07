package com.github.starcatcher21.stargazer.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class StarParticle extends SingleQuadParticle {
    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final int INITIAL_LIFETIME = 300;
    private static final int CURVE_ENDPOINT_TIME = 300;
    private float rotSpeed;
    private final float spinAcceleration;
    private final float windBig;
    private final boolean swirl;
    private final boolean flowAway;
    private final double xaFlowScale;
    private final double zaFlowScale;
    private final double swirlPeriod;

    protected StarParticle(ClientLevel clientLevel, double d, double e, double f, TextureAtlasSprite textureAtlasSprite, float g, float h, boolean bl, boolean bl2, float i, float j) {
        super(clientLevel, d, e, f, textureAtlasSprite);
        this.rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? (double)-30.0F : (double)30.0F);
        this.spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? (double)-5.0F : (double)5.0F);
        this.windBig = h;
        this.swirl = bl;
        this.flowAway = bl2;
        this.lifetime = 300;
        this.gravity = g * 1.2F * 0.0025F;
        float k = i * (this.random.nextBoolean() ? 0.05F : 0.075F);
        this.quadSize = k;
        this.setSize(k, k);
        this.friction = 1.0F;
        this.yd = (double)(-j);
        float l = this.random.nextFloat();
        this.xaFlowScale = Math.cos(Math.toRadians((double)(l * 60.0F))) * (double)this.windBig;
        this.zaFlowScale = Math.sin(Math.toRadians((double)(l * 60.0F))) * (double)this.windBig;
        this.swirlPeriod = Math.toRadians((double)(1000.0F + l * 3000.0F));
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float f = (float)(300 - this.lifetime);
            float g = Math.min(f / 300.0F, 1.0F);
            double d = (double)0.0F;
            double e = (double)0.0F;
            if (this.flowAway) {
                d += this.xaFlowScale * Math.pow((double)g, (double)1.25F);
                e += this.zaFlowScale * Math.pow((double)g, (double)1.25F);
            }

            if (this.swirl) {
                d += (double)g * Math.cos((double)g * this.swirlPeriod) * (double)this.windBig;
                e += (double)g * Math.sin((double)g * this.swirlPeriod) * (double)this.windBig;
            }

            this.xd += d * (double)0.0025F;
            this.zd += e * (double)0.0025F;
            this.yd -= (double)this.gravity;
            this.rotSpeed += this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll += this.rotSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || this.lifetime < 299 && (this.xd == (double)0.0F || this.zd == (double)0.0F)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd *= (double)this.friction;
                this.yd *= (double)this.friction;
                this.zd *= (double)this.friction;
            }
        }
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
            return new StarParticle(clientWorld, d, e, f, this.spriteProvider.get(random), 0.07F, 10.0F, true, false, 2.0F, 0.021F);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class TintedLeavesFactory implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet spriteProvider;

        public TintedLeavesFactory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(
                ColorParticleOption tintedParticleEffect, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random
        ) {
            StarParticle leavesParticle = new StarParticle(clientWorld, d, e, f, this.spriteProvider.get(random), 0.07F, 10.0F, true, false, 2.0F, 0.021F);
            leavesParticle.setColor(tintedParticleEffect.getRed(), tintedParticleEffect.getGreen(), tintedParticleEffect.getBlue());
            return leavesParticle;
        }
    }
}
