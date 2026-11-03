package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.renderer.SkyDimensionChecks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class WorldMixin {

    @Unique
    private boolean shouldDisableWeather() {
        ServerLevel level = (ServerLevel) (Object) this;
        return SkyDimensionChecks.isStargazerSkyDimension(level);
    }

    @Inject(method = "advanceWeatherCycle", at = @At("HEAD"), cancellable = true)
    private void stargazer$disableWeatherInCustomDimensions(CallbackInfo ci) {
        if (SkyDimensionChecks.isStargazerSkyDimension((ServerLevel) (Object) this)) {
            ci.cancel();
        }
    }

    @Inject(method = "advanceWeatherCycle", at = @At("HEAD"), cancellable = true)
    private void onAdvanceWeatherCycle(CallbackInfo ci) {
        if (shouldDisableWeather()) {
            ci.cancel();
        }
    }

    @Inject(method = "tickPrecipitation", at = @At("HEAD"), cancellable = true)
    private void onTickPrecipitation(CallbackInfo ci) {
        if (shouldDisableWeather()) {
            ci.cancel();
        }
    }
}
