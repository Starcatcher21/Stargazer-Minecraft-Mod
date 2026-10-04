package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private Level level;

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

    @Shadow
    public abstract int getMaxFallDistance();

    @Inject(method = "checkFallDamage", at = @At("HEAD"), cancellable = true)
    private void checkFall(double d, boolean bl, BlockState blockState, BlockPos blockPos, CallbackInfo ci) {
        Optional<ResourceKey<DimensionType>> dim = this.level.dimensionTypeRegistration().unwrapKey();
        if (dim.isPresent() && dim.get().equals(CustomWorlds.COSMIC_TYPE)) {
            if (!this.isInWater() && d < 0.0) {
                this.fallDistance -= (float) d / 1.25;
            }

            if (bl) {
                if (this.fallDistance / 1.25 > 0.0) {
                    //? if >= 26.2 {
                    /*blockState.getBlock().fallOn(this.level(), blockState, blockPos, (Entity) (Object) this, this.fallDistance / 1.25);
                    *///? } else {
                    blockState.getBlock().fallOn(this.level(), blockState, blockPos, (Entity) (Object) this, (float) (this.getMaxFallDistance() / 1.25));
                    //? }
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
