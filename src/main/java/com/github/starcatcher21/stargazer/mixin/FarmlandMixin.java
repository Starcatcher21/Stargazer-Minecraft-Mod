package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.block.clases.moon.MoonFarmland;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FarmlandBlock.class)
public class FarmlandMixin {
    @Inject(method = "isWaterNearby", at = @At("TAIL"), cancellable = true)
    private static void add(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (MoonFarmland.isSprinklerNearby(world, pos)) cir.setReturnValue(true);
    }
}
