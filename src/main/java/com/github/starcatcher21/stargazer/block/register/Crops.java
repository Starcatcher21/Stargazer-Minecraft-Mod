package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.moon.plants.GiantDragonCarrot;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Crops {
    public static final Block GIANT_DRAGON_CARROT = register("giant_dragon_carrot", GiantDragonCarrot::new, AbstractBlock.Settings.create()
    );

    public static final Block DRAGON_CARROT_BLOCK = registerWoItem("dragon_carrot_block", (settings) -> new MoonCrop(settings, null), AbstractBlock.Settings.create()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static Item DRAGON_CARROT = ModItems.register("dragon_carrot", ModItems.createBlockItemWithUniqueName(DRAGON_CARROT_BLOCK), new Item.Settings()
            .food(new FoodComponent(6, 12, false))
    );

    public static final Block BROODY_BLOCK = registerWoItem("broody_block", (settings) -> new MoonCrop(settings, null), AbstractBlock.Settings.create()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static Item BROODY = ModItems.register("broody", ModItems.createBlockItemWithUniqueName(BROODY_BLOCK), new Item.Settings()
            .food(new FoodComponent(2, 4, false))
    );

    public static final Block EYE_BALLS_BLOCK = registerWoItem("eye_balls_block", (settings) -> new MoonCrop(settings, null), AbstractBlock.Settings.create()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static Item EYE_BALLS = ModItems.register("eye_balls", ModItems.createBlockItemWithUniqueName(EYE_BALLS_BLOCK), new Item.Settings()
            .food(new FoodComponent(6, 8, false), ConsumableComponent.builder()
                    .consumeEffect(new ConsumeEffect() {
                        @Override
                        public Type<? extends ConsumeEffect> getType() {
                            return Type.APPLY_EFFECTS;
                        }

                        @Override
                        public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
                            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200));
                            return true;
                        }
                    }).build())
    );

    public static void init() {
    }
}
