package com.github.starcatcher21.stargazer.block.clases.moon.plants;

import com.github.starcatcher21.stargazer.block.register.Crops;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.shapes.Shapes;

public class GiantDragonCarrot extends GiantCrop {
    public static final MapCodec<GiantDragonCarrot> CODEC = GiantDragonCarrot.simpleCodec(GiantDragonCarrot::new);

    public MapCodec<GiantDragonCarrot> codec() {
        return CODEC;
    }

    public GiantDragonCarrot(Properties settings) {
        super(settings);
        this.BOTTOM = Shapes.or(
                Shapes.box(0, 0, 0, 0.625, 0.6875, 0.625),
                Shapes.box(0, 0.6875, 0, 0.6875, 1, 0.6875)
        );
        this.TOP = Shapes.or(
                Shapes.box(0, 0, 0, 0.6875, 0.25, 0.6875),
                Shapes.box(0, 0.25, 0, 0.5625, 0.375, 0.5625),
                Shapes.box(0, 0.375, 0, 0.4375, 0.5, 0.4375)
        );
    }

    @Override
    protected ItemLike getSeedsItem() {
        return Crops.DRAGON_CARROT;
    }
}
