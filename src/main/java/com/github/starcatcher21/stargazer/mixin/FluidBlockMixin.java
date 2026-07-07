package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;

@Mixin(LiquidBlock.class)
public class FluidBlockMixin {
    @Inject(method = "onPlace", at = @At("HEAD"), cancellable = true)
    private void add(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        Optional<ResourceKey<DimensionType>> dim = world.dimensionTypeRegistration().unwrapKey();
        if (dim.isPresent() && dim.get().equals(CustomWorlds.COSMIC_TYPE)) {
            if (state.getFluidState().is(FluidTags.WATER)) {
                world.setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
            }
            if (state.getFluidState().is(FluidTags.LAVA)) {
                world.setBlockAndUpdate(pos, Blocks.MAGMA_BLOCK.defaultBlockState());
            }
            ci.cancel();
        }
        if (dim.isPresent() && dim.get().equals(CustomWorlds.RED_ORB_TYPE)) {
            if (state.getFluidState().is(FluidTags.WATER)) {
                world.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
            }
            ci.cancel();
        }
        if (dim.isPresent() && dim.get().equals(CustomWorlds.WANDER_TYPE)) {
            if (state.getFluidState().is(FluidTags.LAVA)) {
                world.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
            }
            ci.cancel();
        }
    }
    @Inject(method = "shouldSpreadLiquid", at = @At("HEAD"), cancellable = true)
    private void water(Level world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        for (CobbleGen gen : CobbleGen.list) {
            if (!gen.gen(world, pos)) {
                cir.setReturnValue(false);
            }
        }
    }
}
