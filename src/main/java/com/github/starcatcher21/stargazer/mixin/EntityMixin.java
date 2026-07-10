package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.datagen.ModFluidTagProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.Set;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private Level level;

    @Mutable
    @Shadow
    @Final
    private EntityFluidInteraction fluidInteraction;

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

    @Shadow
    public abstract boolean isPushedByFluid();

    @Inject(method = "<init>", at = @At("RETURN"))
    private void stargazer$trackDreamFluid(EntityType<?> entityType, Level level, CallbackInfo ci) {
        this.fluidInteraction = new EntityFluidInteraction(Set.of(FluidTags.WATER, FluidTags.LAVA, ModFluidTagProvider.DREAM));
    }

    @Inject(method = "updateFluidInteraction", at = @At("TAIL"), cancellable = true)
    private void stargazer$updateDreamFluidInteraction(CallbackInfoReturnable<Boolean> cir) {
        if (this.fluidInteraction.isInFluid(ModFluidTagProvider.DREAM)) {
            if (this.isPushedByFluid()) {
                this.fluidInteraction.applyCurrentTo(ModFluidTagProvider.DREAM, (Entity) (Object) this, 0.014D);
            }
            cir.setReturnValue(true);
        }
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
