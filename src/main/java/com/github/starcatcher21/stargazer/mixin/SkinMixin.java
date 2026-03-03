package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.mechanics.SkinManager;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.PlayerListEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListEntry.class)
public abstract class SkinMixin {
    @Shadow
    @Final
    private GameProfile profile;

    @Inject(method = "getSkinTextures", at = @At("HEAD"), cancellable = true)
    private void skins(CallbackInfoReturnable cir) {
        String uuid = this.profile.getId().toString();

        if (SkinManager.CACHE.containsKey(uuid)) {
            cir.setReturnValue(SkinManager.CACHE.get(uuid));
        }
    }
}
