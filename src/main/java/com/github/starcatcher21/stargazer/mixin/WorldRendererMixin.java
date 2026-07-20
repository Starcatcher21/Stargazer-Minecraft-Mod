package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.mojang.blaze3d.framegraph.FramePass;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

@Mixin(LevelRenderer.class)
public class WorldRendererMixin {
    @Shadow private SkyRenderer skyRenderer;

    @Redirect(
            method = "addSkyPass",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/framegraph/FramePass;executes(Ljava/lang/Runnable;)V"
            )
    )
    private void redirectFramePassSetRenderer(FramePass framePass, Runnable originalRunnable) {
        framePass.executes(() -> {
            Level world = Minecraft.getInstance().level;
            if (world != null) {
                Optional<ResourceKey<DimensionType>> dim = world.dimensionTypeRegistration().unwrapKey();
                if (dim.isPresent() && (dim.get().equals(CustomWorlds.COSMIC_TYPE) || dim.get().equals(CustomWorlds.RED_ORB_TYPE) || dim.get().equals(CustomWorlds.WANDER_TYPE))) {
                    this.skyRenderer.renderEndSky();
                } else {
                    originalRunnable.run();
                }
            } else {
                originalRunnable.run();
            }
        });
    }
}
