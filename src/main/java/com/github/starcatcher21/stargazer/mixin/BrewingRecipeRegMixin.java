package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
//? if < 26.2 {
import net.minecraft.core.registries.BuiltInRegistries;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionBrewing.class)
public class BrewingRecipeRegMixin {
    @Inject(method = "addVanillaMixes", at = @At("HEAD"), cancellable = true)
    private static void stargazer$reg(PotionBrewing.Builder builder, CallbackInfo ci) {
        //? if >= 26.2 {
        /*builder.addStartMix(ModItems.SUN_ENRICHED_YELLOW_STAR.get(), Potions.CosmoFeel.asHolder());
        builder.addStartMix(ModItems.MOON_GLASS_SHARD.get(), Potions.GlassHands.asHolder());
        builder.addStartMix(Items.ENDER_PEARL, Potions.Hydro.asHolder());
        *///? } else {
        builder.addStartMix(ModItems.SUN_ENRICHED_YELLOW_STAR.get(), BuiltInRegistries.POTION.wrapAsHolder(Potions.CosmoFeel.get()));
        builder.addStartMix(ModItems.MOON_GLASS_SHARD.get(), BuiltInRegistries.POTION.wrapAsHolder(Potions.GlassHands.get()));
        builder.addStartMix(Items.ENDER_PEARL, BuiltInRegistries.POTION.wrapAsHolder(Potions.Hydro.get()));
        //?}
    }
}
