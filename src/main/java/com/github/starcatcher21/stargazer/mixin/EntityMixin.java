package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.datagen.ModFluidTagProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private Level level;

    @Shadow
    public abstract boolean updateFluidHeightAndDoFluidPushing(TagKey<Fluid> tag, double speed);

    @Shadow
    public abstract boolean isInWater();

    @Shadow
    public double fallDistance;

    @Shadow
    public Optional<BlockPos> mainSupportingBlockPos;

    @Shadow
    private Vec3 position;

    @Shadow
    public abstract Level level();

    @Shadow
    public abstract void resetFallDistance();

    @Inject(method = "updateInWaterStateAndDoFluidPushing", at = @At("TAIL"), cancellable = true)
    private void update(CallbackInfoReturnable cir) {
        double d = this.level.environmentAttributes().getDimensionValue(EnvironmentAttributes.FAST_LAVA) ? 0.007 : 0.0023333333333333335;
        boolean bl = this.updateFluidHeightAndDoFluidPushing(FluidTags.LAVA, d);
        boolean bd = this.updateFluidHeightAndDoFluidPushing(ModFluidTagProvider.DREAM, 0.014);
        cir.setReturnValue(this.isInWater() || bl || bd);
    }

    @Inject(method = "checkFallDamage", at = @At("HEAD"), cancellable = true)
    private void checkFall(double d, boolean bl, BlockState blockState, BlockPos blockPos, CallbackInfo ci) {
        Optional<ResourceKey<DimensionType>> dim = this.level.dimensionTypeRegistration().unwrapKey();
        if (dim.isPresent() && dim.get().equals(CustomWorlds.COSMIC_TYPE)) {
            if (!this.isInWater() && d < 0.0) {
                this.fallDistance -= (float) d / 3;
            }

            if (bl) {
                if (this.fallDistance / 3 > 0.0) {
                    blockState.getBlock().fallOn(this.level(), blockState, blockPos, (Entity) (Object) this, this.fallDistance / 3);
                    this.level()
                            .gameEvent(
                                    GameEvent.HIT_GROUND,
                                    this.position,
                                    GameEvent.Context.of((Entity) (Object) this, (BlockState) this.mainSupportingBlockPos.map(blockPosx -> this.level().getBlockState(blockPosx)).orElse(blockState))
                            );
                }

                this.resetFallDistance();
            }
        }
    }
}
