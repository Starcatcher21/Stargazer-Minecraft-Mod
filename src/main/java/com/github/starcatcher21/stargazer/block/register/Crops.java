package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.item.ModItems;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

//? if >= 26.2 {
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
//?}

import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Crops {
    public static final RegistrySupplier<Block> DRAGON_CARROT_BLOCK = registerWoItem(
            "dragon_carrot_block",
            (settings) -> new MoonCrop(settings, null),
            BlockBehaviour.Properties.of()
                    //? if >= 26.2 {
                    .noCollision()
                     //?} else {
                    /*.noCollission()
                    *///?}
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY));

    public static final RegistrySupplier<Item> DRAGON_CARROT = ModItems.register(
            "dragon_carrot",
            ModItems.createBlockItemWithUniqueName(DRAGON_CARROT_BLOCK),
            new Item.Properties().food(
                    //? if >= 26.2 {
                    new FoodProperties(6, 12, false)
                     //?} else {
                    /*new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(12)
                            .build()
                    *///?}
            ));

    public static final RegistrySupplier<Block> BROODY_BLOCK = registerWoItem(
            "broody_block",
            (settings) -> new MoonCrop(settings, null),
            BlockBehaviour.Properties.of()
                    //? if >= 26.2 {
                    .noCollision()
                     //?} else {
                    /*.noCollission()
                    *///?}
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY));

    public static RegistrySupplier<Item> BROODY = ModItems.register(
            "broody",
            ModItems.createBlockItemWithUniqueName(BROODY_BLOCK),
            new Item.Properties().food(
                    //? if >= 26.2 {
                    new FoodProperties(2, 4, false)
                     //?} else {
                    /*new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(4)
                            .build()
                    *///?}
            ));

    public static final RegistrySupplier<Block> EYE_BALLS_BLOCK = registerWoItem(
            "eye_balls_block",
            (settings) -> new MoonCrop(settings, null),
            BlockBehaviour.Properties.of()
                    //? if >= 26.2 {
                    .noCollision()
                     //?} else {
                    /*.noCollission()
                    *///?}
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY));

    public static RegistrySupplier<Item> EYE_BALLS = ModItems.register(
            "eye_balls",
            ModItems.createBlockItemWithUniqueName(EYE_BALLS_BLOCK),
            //? if >= 26.2 {
            new Item.Properties()
                    .food(new FoodProperties(6, 8, false), Consumable.builder()
                            .onConsume(new ConsumeEffect() {
                                @Override
                                public Type<? extends ConsumeEffect> getType() {
                                    return Type.APPLY_EFFECTS;
                                }

                                @Override
                                public boolean apply(Level world, ItemStack stack, LivingEntity user) {
                                    user.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200));
                                    return true;
                                }
                            })
                            .build())
            //?} else {
            /*new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(8)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1.0f)
                            .build()
            )
            *///?}
    );

    public static void init() {
    }
}
