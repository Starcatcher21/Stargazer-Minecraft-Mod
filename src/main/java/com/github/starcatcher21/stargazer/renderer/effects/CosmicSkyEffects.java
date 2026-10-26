//? if neoforge && < 26.2 {
package com.github.starcatcher21.stargazer.renderer.effects;

import com.github.starcatcher21.stargazer.renderer.SkyStarRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public final class CosmicSkyEffects extends DimensionSpecialEffects {

    public CosmicSkyEffects() {
        // cloudLevel, hasGround, skyType, forceBrightLightmap, constantAmbientLight
        super(Float.NaN, false, SkyType.NONE, false, false);
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        PoseStack poseStack = new PoseStack();
        poseStack.mulPose(modelViewMatrix);
        SkyStarRenderer.renderDirect(poseStack, modelViewMatrix, level, camera.getPosition(), level.getGameTime());
        return  true;
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
        return Vec3.ZERO; // no fog tint
    }

    @Override
    public boolean isFoggyAt(int x, int z) {
        return false;
    }
}
//? }
