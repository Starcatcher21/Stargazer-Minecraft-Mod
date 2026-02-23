package com.github.starcatcher21.stargazer.block.clases.moon.plants;

import com.github.starcatcher21.stargazer.block.register.Crops;
import com.mojang.serialization.MapCodec;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.shape.VoxelShapes;

public class GiantDragonCarrot extends GiantCrop {
    public static final MapCodec<GiantDragonCarrot> CODEC = GiantDragonCarrot.createCodec(GiantDragonCarrot::new);

    public MapCodec<GiantDragonCarrot> getCodec() {
        return CODEC;
    }

    public GiantDragonCarrot(Settings settings) {
        super(settings);
        this.BOTTOM = VoxelShapes.union(
                VoxelShapes.cuboid(0, 0, 0, 0.625, 0.6875, 0.625),
                VoxelShapes.cuboid(0, 0.6875, 0, 0.6875, 1, 0.6875)
        );
        this.TOP = VoxelShapes.union(
                VoxelShapes.cuboid(0, 0, 0, 0.6875, 0.25, 0.6875),
                VoxelShapes.cuboid(0, 0.25, 0, 0.5625, 0.375, 0.5625),
                VoxelShapes.cuboid(0, 0.375, 0, 0.4375, 0.5, 0.4375)
        );
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return Crops.DRAGON_CARROT;
    }
}
