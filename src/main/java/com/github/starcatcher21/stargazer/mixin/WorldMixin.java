package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.ServerWorldProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ServerWorld.class)
public abstract class WorldMixin {

    @Shadow
    @Final
    private ServerWorldProperties worldProperties;

    @Shadow
    public abstract ServerWorld toServerWorld();

    @Shadow
    public abstract MinecraftServer getServer();

    @Inject(method = "tickWeather", at = @At("HEAD"), cancellable = true)
    private void consmicWeather(CallbackInfo ci) {
        World world = MinecraftClient.getInstance().world;
        if (world != null) {
            Optional<RegistryKey<DimensionType>> dim = world.getDimensionEntry().getKey();
            if (dim.isPresent() && dim.get().equals(CustomWorlds.COSMIC_TYPE)) {
                ci.cancel();
            }
        }
    }
}
