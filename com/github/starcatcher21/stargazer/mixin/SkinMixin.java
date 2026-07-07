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

import java.util.ArrayList;

// TODO(Ravel): can not resolve target class PlayerListEntry
// TODO(Ravel): can not resolve target class PlayerListEntry
@Mixin(PlayerListEntry.class)
public abstract class SkinMixin {
    private static ArrayList<String> uuids = new ArrayList<>();
    private static int timeout = 0;
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    @Final
    private GameProfile profile;

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "getSkinTextures", at = @At("HEAD"), cancellable = true)
    private void skins(CallbackInfoReturnable cir) {
        String uuid = this.profile.id().toString();

        if (SkinManager.CACHE.containsKey(uuid)) {
            cir.setReturnValue(SkinManager.CACHE.get(uuid));
        } else {
            if (timeout <= 0) {
                if (!uuids.contains(uuid)) {
                    SkinManager.fetchRemoteSkins();
                    uuids.add(uuid);
                    timeout = 12000;
                }
            } else {
                timeout -= 1;
            }
        }
    }
}
