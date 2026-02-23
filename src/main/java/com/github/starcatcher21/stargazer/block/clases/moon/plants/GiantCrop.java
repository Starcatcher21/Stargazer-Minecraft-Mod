package com.github.starcatcher21.stargazer.block.clases.moon.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class GiantCrop
        extends Block {
    public static final MapCodec<GiantCrop> CODEC = GiantCrop.createCodec(GiantCrop::new);
    public static final EnumProperty<GiantCropSide> SIDE = EnumProperty.of("side", GiantCropSide.class);
    public VoxelShape BOTTOM = VoxelShapes.cuboid(0, 0, 0, 0.6875, 1, 0.6875);
    public VoxelShape TOP = VoxelShapes.cuboid(0, 0, 0, 0.6875, 0.5, 0.6875);

    public MapCodec<? extends GiantCrop> getCodec() {
        return CODEC;
    }


    public GiantCrop(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SIDE, GiantCropSide.ned));
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        for (BlockPos blockPos : BlockPos.iterate(getBox(pos, state))) {
            world.breakBlock(blockPos, false);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        place(world, pos, state);
    }

    public static void place(World world, BlockPos pos, BlockState state) {
        GiantCropSide side = GiantCropSide.nwd;
        for (BlockPos blockPos : BlockPos.iterate(getBox(pos, state))) {
            world.setBlockState(blockPos, state.with(SIDE, side));
            side = nexPlaceState(side);
        }
    }

    private static GiantCropSide nexPlaceState(GiantCropSide state) {
        if (state.equals(GiantCropSide.nwd)) {
            return GiantCropSide.ned;
        } else if (state.equals(GiantCropSide.ned)) {
            return GiantCropSide.nwu;
        } else if (state.equals(GiantCropSide.nwu)) {
            return GiantCropSide.neu;
        } else if (state.equals(GiantCropSide.neu)) {
            return GiantCropSide.swd;
        } else if (state.equals(GiantCropSide.swd)) {
            return GiantCropSide.sed;
        } else if (state.equals(GiantCropSide.sed)) {
            return GiantCropSide.swu;
        } else if (state.equals(GiantCropSide.swu)) {
            return GiantCropSide.seu;
        } else {
            return GiantCropSide.nwd;
        }
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.iterate(getBox(pos, state))) {
            if (!world.getBlockState(blockPos).isIn(BlockTags.REPLACEABLE) && !world.getBlockState(blockPos).isAir()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Map<Direction, VoxelShape> B = VoxelShapes.createFacingShapeMap(BOTTOM);
        Map<Direction, VoxelShape> T = VoxelShapes.createFacingShapeMap(TOP);
        if (state.get(SIDE).equals(GiantCropSide.sed)) {
            return B.get(Direction.NORTH);
        } else if (state.get(SIDE).equals(GiantCropSide.swd)) {
            return B.get(Direction.EAST);
        } else if (state.get(SIDE).equals(GiantCropSide.ned)) {
            return B.get(Direction.WEST);
        } else if (state.get(SIDE).equals(GiantCropSide.nwd)) {
            return B.get(Direction.SOUTH);
        } else if (state.get(SIDE).equals(GiantCropSide.seu)) {
            return T.get(Direction.NORTH);
        } else if (state.get(SIDE).equals(GiantCropSide.swu)) {
            return T.get(Direction.EAST);
        } else if (state.get(SIDE).equals(GiantCropSide.neu)) {
            return T.get(Direction.WEST);
        } else if (state.get(SIDE).equals(GiantCropSide.nwu)) {
            return T.get(Direction.SOUTH);
        } else {
            return TOP;
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }

    //box to fix wrong spawn ahh i need to fix that
    public static Box getBox(BlockPos pos, BlockState state) {
        if (state.get(SIDE).equals(GiantCropSide.ned)) {
            // good
            return new Box(Vec3d.of(pos), Vec3d.of(pos.up().west().south()));
        } else if (state.get(SIDE).equals(GiantCropSide.neu)) {
            return new Box(Vec3d.of(pos.down()), Vec3d.of(pos.west().south()));
        } else if (state.get(SIDE).equals(GiantCropSide.nwd)) {
            // good
            return new Box(Vec3d.of(pos.east()), Vec3d.of(pos.up().south()));
        } else if (state.get(SIDE).equals(GiantCropSide.nwu)) {
            return new Box(Vec3d.of(pos.east().down()), Vec3d.of(pos.west().south()));
        } else if (state.get(SIDE).equals(GiantCropSide.sed)) {
            // good
            return new Box(Vec3d.of(pos.north()), Vec3d.of(pos.up().west()));
        } else if (state.get(SIDE).equals(GiantCropSide.seu)) {
            return new Box(Vec3d.of(pos.down().north()), Vec3d.of(pos.west()));
        } else if (state.get(SIDE).equals(GiantCropSide.swd)) {
            //good
            return new Box(Vec3d.of(pos.east().north()),Vec3d.of(pos.up()));
        } else if (state.get(SIDE).equals(GiantCropSide.swu)) {
            return new Box(Vec3d.of(pos.east().down().north()),Vec3d.of(pos));
        }
        return new Box(pos);
    }


    protected ItemConvertible getSeedsItem() {
        return Items.WHEAT_SEEDS;
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SIDE);
    }
}