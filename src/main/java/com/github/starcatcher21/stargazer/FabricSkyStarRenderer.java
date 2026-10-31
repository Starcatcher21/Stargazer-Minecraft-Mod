//? if fabric {
/*package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.renderer.SkyDimensionChecks;
import com.github.starcatcher21.stargazer.renderer.SkyStarRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;

public final class FabricSkyStarRenderer {
    private static final FabricSkyStarRenderer INSTANCE = new FabricSkyStarRenderer();

    private boolean enabled = false;
    private long gameTime = 0L;
    private Vec3 cameraPos = Vec3.ZERO;

    public static void init() {
        LevelExtractionEvents.END_EXTRACTION.register(INSTANCE::extract);
        LevelRenderEvents.END_MAIN.register(INSTANCE::renderAndDraw);
    }

    private void extract(LevelExtractionContext context) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null || !SkyDimensionChecks.isStargazerSkyDimension(level)) {
            this.enabled = false;
            return;
        }

        this.enabled = true;
        this.gameTime = level.getGameTime();
        this.cameraPos = context.levelState().cameraRenderState.pos;
    }

    private void renderAndDraw(LevelRenderContext context) {
        if (!this.enabled) {
            return;
        }

        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            SkyStarRenderer.renderDirect(
                    context.poseStack(),
                    level,
                    this.cameraPos,
                    this.gameTime
            );
        }
    }
}
*///? }
