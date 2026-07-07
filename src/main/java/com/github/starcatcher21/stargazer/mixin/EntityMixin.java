package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.datagen.ModFluidTagProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private Level level;

    @Shadow
    public abstract boolean updateFluidHeightAndDoFluidPushing(TagKey<Fluid> tag, double speed);

    @Shadow
    public abstract boolean isInWater();

    @Inject(method = "updateInWaterStateAndDoFluidPushing", at = @At("TAIL"), cancellable = true)
    private void update(CallbackInfoReturnable cir) {
        double d = this.level.environmentAttributes().getDimensionValue(EnvironmentAttributes.FAST_LAVA) ? 0.007 : 0.0023333333333333335;
        boolean bl = this.updateFluidHeightAndDoFluidPushing(FluidTags.LAVA, d);
        boolean bd = this.updateFluidHeightAndDoFluidPushing(ModFluidTagProvider.DREAM, 0.014);
        cir.setReturnValue(this.isInWater() || bl || bd);
    }
}
