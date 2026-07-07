package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ServerLevel.class)
public abstract class WorldMixin {

    @Shadow
    @Final
    private ServerLevelData serverLevelData;

    @Shadow
    public abstract ServerLevel getLevel();

    @Shadow
    public abstract MinecraftServer getServer();

    @Inject(method = "advanceWeatherCycle", at = @At("HEAD"), cancellable = true)
    private void consmicWeather(CallbackInfo ci) {
        Level world = Minecraft.getInstance().level;
        if (world != null) {
            Optional<ResourceKey<DimensionType>> dim = world.dimensionTypeRegistration().unwrapKey();
            if (dim.isPresent() && (dim.get().equals(CustomWorlds.COSMIC_TYPE) || dim.get().equals(CustomWorlds.WANDER_TYPE) || dim.get().equals(CustomWorlds.RED_ORB_TYPE))) {
                ci.cancel();
            }
        }
    }
}
