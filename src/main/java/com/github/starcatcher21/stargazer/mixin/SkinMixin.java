package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.mechanics.SkinManager;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Supplier;

@Mixin(PlayerListEntry.class)
public abstract class SkinMixin {
    @Unique
    private static final List<String> SKINS = List.of("textures/entity/player/slim/alex.png", "textures/entity/player/slim/ari.png", "textures/entity/player/slim/efe.png", "textures/entity/player/slim/kai.png", "textures/entity/player/slim/makena.png", "textures/entity/player/slim/noor.png", "textures/entity/player/slim/steve.png", "textures/entity/player/slim/sunny.png", "textures/entity/player/slim/zuri.png", "textures/entity/player/wide/alex.png", "textures/entity/player/wide/ari.png", "textures/entity/player/wide/efe.png", "textures/entity/player/wide/kai.png", "textures/entity/player/wide/makena.png", "textures/entity/player/wide/noor.png", "textures/entity/player/wide/steve.png", "textures/entity/player/wide/sunny.png", "textures/entity/player/wide/zuri.png");

    @Shadow
    @Final
    private GameProfile profile;

    @Shadow
    @Final
    private Supplier<SkinTextures> texturesSupplier;

    @Inject(method = "getSkinTextures", at = @At("HEAD"), cancellable = true)
    private void skins(CallbackInfoReturnable cir) {
        String uuid = this.profile.getId().toString();

        if (SkinManager.CACHE.containsKey(uuid)) {
            cir.setReturnValue(SkinManager.CACHE.get(uuid));
        }
    }
}
