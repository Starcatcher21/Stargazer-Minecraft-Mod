package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow @Final private MinecraftClient client;

    @Shadow private SkyRendering skyRendering;
    @Shadow private CloudRenderer cloudRenderer;

    @Shadow
    @Final
    private DefaultFramebufferSet framebufferSet;

    @Shadow
    @Final
    private WorldRenderState worldRenderState;

    @Redirect(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/FramePass;setRenderer(Ljava/lang/Runnable;)V"
            )
    )
    private void redirectFramePassSetRenderer(FramePass framePass, Runnable originalRunnable) {
        framePass.setRenderer(() -> {
            World world = client.world;
            if (world != null) {
                Optional<RegistryKey<DimensionType>> dim = world.getDimensionEntry().getKey();
                if (dim.isPresent() && (dim.get().equals(CustomWorlds.COSMIC_TYPE) || dim.get().equals(CustomWorlds.RED_ORB_TYPE) || dim.get().equals(CustomWorlds.WANDER_TYPE))) {
                    this.skyRendering.renderEndSky();
                } else {
                    originalRunnable.run();
                }
            } else {
                originalRunnable.run();
            }
        });
    }
}
