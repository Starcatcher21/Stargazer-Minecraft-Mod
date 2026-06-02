package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.item.Items;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegMixin {
    @Inject(method = "registerDefaults", at = @At("HEAD"), cancellable = true)
    private static void reg(BrewingRecipeRegistry.Builder builder, CallbackInfo ci) {
        builder.registerRecipes(ModItems.SUN_ENRICHED_YELLOW_STAR, Potions.CosmoFeel);
        builder.registerRecipes(ModItems.MOON_GLASS_SHARD, Potions.GlassHands);
        builder.registerRecipes(Items.ENDER_PEARL, Potions.Hydro);
    }
}
