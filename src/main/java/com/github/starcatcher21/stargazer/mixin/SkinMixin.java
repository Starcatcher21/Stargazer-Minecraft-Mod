package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class SkinMixin {
    @Unique
    private static final List<String> SKINS = List.of("textures/entity/player/slim/alex.png", "textures/entity/player/slim/ari.png", "textures/entity/player/slim/efe.png", "textures/entity/player/slim/kai.png", "textures/entity/player/slim/makena.png", "textures/entity/player/slim/noor.png", "textures/entity/player/slim/steve.png", "textures/entity/player/slim/sunny.png", "textures/entity/player/slim/zuri.png", "textures/entity/player/wide/alex.png", "textures/entity/player/wide/ari.png", "textures/entity/player/wide/efe.png", "textures/entity/player/wide/kai.png", "textures/entity/player/wide/makena.png", "textures/entity/player/wide/noor.png", "textures/entity/player/wide/steve.png", "textures/entity/player/wide/sunny.png", "textures/entity/player/wide/zuri.png");

    @Shadow
    protected abstract @Nullable PlayerListEntry getPlayerListEntry();

    @Unique
    private static final Identifier skin = Identifier.of(Stargazer.MOD_ID, "textures/entity/player/slim/star_catcher_.png");
    @Unique
    private static final Identifier cape = Identifier.of(Stargazer.MOD_ID, "textures/entity/player/capes/star_catcher_cape.png");

    @Inject(method = "getSkinTextures", at = @At("HEAD"), cancellable = true)
    private void skins(CallbackInfoReturnable cir) {
        PlayerListEntry list = this.getPlayerListEntry();
        if (list != null && list.getProfile().getName().equals("star_catcher_")) {
            SkinTextures skk = list.getSkinTextures();
            Identifier ogskin = list.getSkinTextures().texture();
            if (SKINS.contains(ogskin.getPath())) {
                cir.setReturnValue(new SkinTextures(skin, null, cape, cape, SkinTextures.Model.SLIM, true));
            } else {
                cir.setReturnValue(new SkinTextures(ogskin, null, cape, cape, skk.model(), true));
            }
        }
    }
}
