// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.moon.plants.GiantDragonCarrot;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Crops {
    public static final Block GIANT_DRAGON_CARROT = register("giant_dragon_carrot", GiantDragonCarrot::new, BlockBehaviour.Properties.of()
    );

    public static final Block DRAGON_CARROT_BLOCK = registerWoItem("dragon_carrot_block", (settings) -> new MoonCrop(settings, null), BlockBehaviour.Properties.of()
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .pushReaction(PushReaction.DESTROY)
    );

    public static Item DRAGON_CARROT = ModItems.register("dragon_carrot", ModItems.createBlockItemWithUniqueName(DRAGON_CARROT_BLOCK), new Item.Properties()
            .food(new FoodProperties(6, 12, false))
    );

    public static final Block BROODY_BLOCK = registerWoItem("broody_block", (settings) -> new MoonCrop(settings, null), BlockBehaviour.Properties.of()
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .pushReaction(PushReaction.DESTROY)
    );

    public static Item BROODY = ModItems.register("broody", ModItems.createBlockItemWithUniqueName(BROODY_BLOCK), new Item.Properties()
            .food(new FoodProperties(2, 4, false))
    );

    public static final Block EYE_BALLS_BLOCK = registerWoItem("eye_balls_block", (settings) -> new MoonCrop(settings, null), BlockBehaviour.Properties.of()
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .pushReaction(PushReaction.DESTROY)
    );

    public static Item EYE_BALLS = ModItems.register("eye_balls", ModItems.createBlockItemWithUniqueName(EYE_BALLS_BLOCK), new Item.Properties()
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
                    }).build())
    );

    public static void init() {
    }
}
