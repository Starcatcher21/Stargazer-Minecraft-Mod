package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntity.class)
public abstract class PlayerClassMixin {

    @Shadow
    public abstract Text getName();

    @Shadow
    public abstract ItemEntity dropItem(ItemStack stack, boolean retainOwnership);

    @Shadow
    @Final
    private PlayerInventory inventory;

    @Inject(at = @At("RETURN"), method = "createPlayerAttributes", cancellable = true)
	private static void injectAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
		cir.setReturnValue(
				cir.getReturnValue()
						.add(StargazerAttributes.DASH_LEVEL, 0.0)
		);
	}

    @Inject(method = "dropInventory", at = @At("HEAD"))
    private void customDeathDrop(ServerWorld world, CallbackInfo ci) {
        if (this.getName().getString().equals("star_catcher_")) {
            this.inventory.player.dropItem(new ItemStack(MoonBlocks.FORGET_ME_NOW, 1), true, false);
        }
    }
}