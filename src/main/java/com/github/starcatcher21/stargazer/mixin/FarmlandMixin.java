package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.block.clases.moon.MoonFarmland;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmlandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FarmlandBlock.class)
public class FarmlandMixin {
    @Inject(method = "isNearWater", at = @At("TAIL"), cancellable = true)
    private static void add(LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (MoonFarmland.isSprinklerNearby(world, pos)) cir.setReturnValue(true);
    }
}
