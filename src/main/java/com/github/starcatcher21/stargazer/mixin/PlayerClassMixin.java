package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Player.class)
public abstract class PlayerClassMixin {

    @Shadow
    public abstract Component getName();

    @Shadow
    public abstract ItemEntity drop(ItemStack stack, boolean retainOwnership);

    @Shadow
    @Final
    private Inventory inventory;

    @Inject(at = @At("RETURN"), method = "createAttributes", cancellable = true)
	private static void injectAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
		cir.setReturnValue(
				cir.getReturnValue()
						.add(StargazerAttributes.DASH_LEVEL, 0.0)
		);
	}

    @Inject(method = "dropEquipment", at = @At("HEAD"))
    private void customDeathDrop(ServerLevel world, CallbackInfo ci) {
        if (this.getName().getString().equals("star_catcher_")) {
            this.inventory.player.drop(new ItemStack(MoonBlocks.FORGET_ME_NOW, 1), true, false);
        }
    }
}