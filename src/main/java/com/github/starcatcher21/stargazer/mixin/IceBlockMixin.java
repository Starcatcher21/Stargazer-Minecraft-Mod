package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;

@Mixin(IceBlock.class)
public class IceBlockMixin {
    @Inject(method = "playerDestroy", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"
    ), cancellable = true)
    private void notMelt(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        Optional<ResourceKey<DimensionType>> dim = world.dimensionTypeRegistration().unwrapKey();
        if (dim.isPresent() && (dim.get().equals(CustomWorlds.COSMIC_TYPE) || dim.get().equals(CustomWorlds.RED_ORB_TYPE))) {
            ci.cancel();
        }
    }
}
