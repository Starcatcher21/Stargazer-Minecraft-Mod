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
        builder.registerRecipes(ModItems.YELLOW_STAR, Potions.CosmoFeel);
        builder.registerRecipes(Items.GLASS, Potions.GlassHands);
        builder.registerRecipes(Items.WATER_BUCKET, Potions.Hydro);
    }
}
