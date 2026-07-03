package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.datagen.ModFluidTagProvider;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;
import net.minecraft.world.attribute.EnvironmentAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private World world;

    @Shadow
    public abstract boolean updateMovementInFluid(TagKey<Fluid> tag, double speed);

    @Shadow
    public abstract boolean isTouchingWater();

    @Inject(method = "updateWaterState", at = @At("TAIL"), cancellable = true)
    private void update(CallbackInfoReturnable cir) {
        double d = this.world.getEnvironmentAttributes().getAttributeValue(EnvironmentAttributes.FAST_LAVA_GAMEPLAY) ? 0.007 : 0.0023333333333333335;
        boolean bl = this.updateMovementInFluid(FluidTags.LAVA, d);
        boolean bd = this.updateMovementInFluid(ModFluidTagProvider.DREAM, 0.014);
        cir.setReturnValue(this.isTouchingWater() || bl || bd);
    }
}
