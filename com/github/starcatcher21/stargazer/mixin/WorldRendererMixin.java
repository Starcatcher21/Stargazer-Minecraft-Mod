package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.FramePass;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

// TODO(Ravel): can not resolve target class WorldRenderer
// TODO(Ravel): can not resolve target class WorldRenderer
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow @Final private MinecraftClient client;

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow private SkyRendering skyRendering;

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
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
