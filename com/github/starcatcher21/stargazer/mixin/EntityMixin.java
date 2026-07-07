package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.datagen.ModFluidTagProvider;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;
import ceg.ceg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO(Ravel): can not resolve target class Entity
// TODO(Ravel): can not resolve target class Entity
@Mixin(Entity.class)
public abstract class EntityMixin {
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    private World world;

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean updateMovementInFluid(TagKey<Fluid> tag, double speed);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean isTouchingWater();

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "updateWaterState", at = @At("TAIL"), cancellable = true)
    private void update(CallbackInfoReturnable cir) {
        double d = this.world.getEnvironmentAttributes().getAttributeValue(ceg.FAST_LAVA_GAMEPLAY) ? 0.007 : 0.0023333333333333335;
        boolean bl = this.updateMovementInFluid(FluidTags.LAVA, d);
        boolean bd = this.updateMovementInFluid(ModFluidTagProvider.DREAM, 0.014);
        cir.setReturnValue(this.isTouchingWater() || bl || bd);
    }
}
