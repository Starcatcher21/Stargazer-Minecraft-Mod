package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionBrewing.class)
public class BrewingRecipeRegMixin {
    @Inject(method = "addVanillaMixes", at = @At("HEAD"), cancellable = true)
    private static void reg(PotionBrewing.Builder builder, CallbackInfo ci) {
        builder.addStartMix(ModItems.SUN_ENRICHED_YELLOW_STAR, Potions.CosmoFeel);
        builder.addStartMix(ModItems.MOON_GLASS_SHARD, Potions.GlassHands);
        builder.addStartMix(Items.ENDER_PEARL, Potions.Hydro);
    }
}
