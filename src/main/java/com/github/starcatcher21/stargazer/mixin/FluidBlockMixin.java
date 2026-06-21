package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerDataLoader;
import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {
    @Inject(method = "onBlockAdded", at = @At("HEAD"), cancellable = true)
    private void add(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        Optional<RegistryKey<DimensionType>> dim = world.getDimensionEntry().getKey();
        if (dim.isPresent() && dim.get().equals(CustomWorlds.COSMIC_TYPE)) {
            if (state.getFluidState().isIn(FluidTags.WATER)) {
                world.setBlockState(pos, Blocks.ICE.getDefaultState());
            }
            if (state.getFluidState().isIn(FluidTags.LAVA)) {
                world.setBlockState(pos, Blocks.MAGMA_BLOCK.getDefaultState());
            }
            ci.cancel();
        }
        if (dim.isPresent() && dim.get().equals(CustomWorlds.RED_ORB_TYPE)) {
            if (state.getFluidState().isIn(FluidTags.WATER)) {
                world.setBlockState(pos, Blocks.FIRE.getDefaultState());
            }
            ci.cancel();
        }
    }
    @Inject(method = "receiveNeighborFluids", at = @At("HEAD"), cancellable = true)
    private void water(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        for (CobbleGen gen : StargazerDataLoader.getCobbelgenData().values()) {
            if (!gen.gen(world, pos)) {
                cir.setReturnValue(false);
            }
        }
    }
}
