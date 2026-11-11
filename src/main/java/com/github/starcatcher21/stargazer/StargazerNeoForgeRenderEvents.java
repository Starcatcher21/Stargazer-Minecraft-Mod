//? if neoforge {
package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.renderer.SkyStarRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

//@EventBusSubscriber(modid = Stargazer.MOD_ID, value = Dist.CLIENT)
public class StargazerNeoForgeRenderEvents {

    //? if >= 26.1.2 {
    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent.AfterSky event) {
        Minecraft client = Minecraft.getInstance();
        //? if >= 26.2 {
        Camera camera = client.gameRenderer.mainCamera();
        //? } else {
        /*Camera camera = client.gameRenderer.getMainCamera();
        *///? }
        if (client.level != null && camera != null) {
            Vec3 cameraPos = camera.position();

            SkyStarRenderer.renderDirect(
                    event.getPoseStack(),
                    client.level,
                    cameraPos,
                    client.level.getGameTime()
            );
        }
    }
    //? } else {
    /*@SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_SKY)) {
            Minecraft client = Minecraft.getInstance();
            if (client.level != null && client.gameRenderer.getMainCamera() != null) {
                Vec3 cameraPos = client.gameRenderer.getMainCamera().getPosition();
                event.getPoseStack().mulPose(event.getModelViewMatrix());

                SkyStarRenderer.renderDirect(
                        event.getPoseStack(),
                        event.getModelViewMatrix(),
                        client.level,
                        cameraPos,
                        client.level.getGameTime()
                );
            }
        }
    }
    *///? }
}
//? }
