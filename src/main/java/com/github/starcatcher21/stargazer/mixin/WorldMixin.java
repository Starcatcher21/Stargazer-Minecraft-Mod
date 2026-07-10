package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class WorldMixin {

    @Inject(method = "advanceWeatherCycle", at = @At("HEAD"), cancellable = true)
    private void stargazer$disableWeatherInCustomDimensions(CallbackInfo ci) {
        ResourceKey<Level> dimension = ((ServerLevel) (Object) this).dimension();
        if (dimension.equals(CustomWorlds.COSMIC) || dimension.equals(CustomWorlds.WANDER) || dimension.equals(CustomWorlds.RED_ORB)) {
            ci.cancel();
        }
    }
}
