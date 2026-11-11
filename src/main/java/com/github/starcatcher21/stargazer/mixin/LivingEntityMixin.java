package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.datagen.FluidTagProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void stargazer$dreamSwim(Vec3 input, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;

        FluidState fluid = self.level().getFluidState(self.blockPosition());
        if (!fluid.is(FluidTagProvider.DREAM)) return;

        self.moveRelative(-0.01F, input);
        Vec3 look = self.getLookAngle();
        self.setDeltaMovement(self.getDeltaMovement().add(0, look.y * input.z * -0.01, 0));
        self.move(MoverType.SELF, self.getDeltaMovement());
        self.setDeltaMovement(self.getDeltaMovement().scale(0.92));
        ci.cancel();
    }
}
