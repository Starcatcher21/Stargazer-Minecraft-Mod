package com.github.starcatcher21.stargazer.neoforge.client;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.renderer.SkyStarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = Stargazer.MOD_ID, value = Dist.CLIENT)
public class StargazerNeoForgeRenderEvents {

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent.AfterSky event) {
        Minecraft client = Minecraft.getInstance();
        if (client.level != null && client.gameRenderer.mainCamera() != null) {
            Vec3 cameraPos = client.gameRenderer.mainCamera().position();

            SkyStarRenderer.renderDirect(
                    event.getPoseStack(),
                    client.level,
                    cameraPos,
                    client.level.getGameTime()
            );
        }
    }
}