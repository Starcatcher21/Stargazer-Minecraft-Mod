package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.moon.plants.GiantDragonCarrot;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;
import static com.github.starcatcher21.stargazer.block.ModBlock.registerWoItem;

public class Crops {
    public static final Block GIANT_DRAGON_CARROT = register("giant_dragon_carrot", GiantDragonCarrot::new, AbstractBlock.Settings.create()
    );

    public static final Block DRAGON_CARROT_BLOCK = registerWoItem("dragon_carrot_block", (settings) -> new MoonCrop(settings, GIANT_DRAGON_CARROT), AbstractBlock.Settings.create()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static Item DRAGON_CARROT = ModItems.register("dragon_carrot", ModItems.createBlockItemWithUniqueName(DRAGON_CARROT_BLOCK), new Item.Settings()
            .food(new FoodComponent(6, 12, false))
    );

    public static void init() {
    }
}
