package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.Stargazer;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.*;
import net.minecraft.client.render.state.SkyRenderState;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
