package com.github.starcatcher21.stargazer.block.clases.moon.plants;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GiantCrop
        extends Block {
    public static final MapCodec<GiantCrop> CODEC = GiantCrop.simpleCodec(GiantCrop::new);
    public static final EnumProperty<GiantCropSide> SIDE = EnumProperty.create("side", GiantCropSide.class);
    public VoxelShape BOTTOM = Shapes.box(0, 0, 0, 0.6875, 1, 0.6875);
    public VoxelShape TOP = Shapes.box(0, 0, 0, 0.6875, 0.5, 0.6875);

    public MapCodec<? extends GiantCrop> codec() {
        return CODEC;
    }


    public GiantCrop(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(SIDE, GiantCropSide.ned));
    }

    @Override
    public void destroy(LevelAccessor world, BlockPos pos, BlockState state) {
        super.destroy(world, pos, state);
        for (BlockPos blockPos : getBlockPositions(getBox(pos, state))) {
            world.destroyBlock(blockPos, false);
        }
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.setPlacedBy(world, pos, state, placer, itemStack);
        place(world, pos, state);
    }

    public static void place(Level world, BlockPos pos, BlockState state) {
        GiantCropSide side = GiantCropSide.nwd;
        for (BlockPos blockPos : getBlockPositions(getBox(pos, state))) {
            world.setBlockAndUpdate(blockPos, state.setValue(SIDE, side));
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
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        for (BlockPos blockPos : getBlockPositions(getBox(pos, state))) {
            if (!world.getBlockState(blockPos).is(BlockTags.REPLACEABLE) && !world.getBlockState(blockPos).isAir()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(SIDE).equals(GiantCropSide.sed)) {
            return BOTTOM;
        } else if (state.getValue(SIDE).equals(GiantCropSide.swd)) {
            return BOTTOM;
        } else if (state.getValue(SIDE).equals(GiantCropSide.ned)) {
            return BOTTOM;
        } else if (state.getValue(SIDE).equals(GiantCropSide.nwd)) {
            return BOTTOM;
        } else if (state.getValue(SIDE).equals(GiantCropSide.seu)) {
            return TOP;
        } else if (state.getValue(SIDE).equals(GiantCropSide.swu)) {
            return TOP;
        } else if (state.getValue(SIDE).equals(GiantCropSide.neu)) {
            return TOP;
        } else if (state.getValue(SIDE).equals(GiantCropSide.nwu)) {
            return TOP;
        } else {
            return TOP;
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getShape(state, world, pos, context);
    }

    //box to fix wrong spawn ahh i need to fix that
    public static AABB getBox(BlockPos pos, BlockState state) {
        if (state.getValue(SIDE).equals(GiantCropSide.ned)) {
            // good
            return new AABB(Vec3.atLowerCornerOf(pos), Vec3.atLowerCornerOf(pos.above().west().south()));
        } else if (state.getValue(SIDE).equals(GiantCropSide.neu)) {
            return new AABB(Vec3.atLowerCornerOf(pos.below()), Vec3.atLowerCornerOf(pos.west().south()));
        } else if (state.getValue(SIDE).equals(GiantCropSide.nwd)) {
            // good
            return new AABB(Vec3.atLowerCornerOf(pos.east()), Vec3.atLowerCornerOf(pos.above().south()));
        } else if (state.getValue(SIDE).equals(GiantCropSide.nwu)) {
            return new AABB(Vec3.atLowerCornerOf(pos.east().below()), Vec3.atLowerCornerOf(pos.west().south()));
        } else if (state.getValue(SIDE).equals(GiantCropSide.sed)) {
            // good
            return new AABB(Vec3.atLowerCornerOf(pos.north()), Vec3.atLowerCornerOf(pos.above().west()));
        } else if (state.getValue(SIDE).equals(GiantCropSide.seu)) {
            return new AABB(Vec3.atLowerCornerOf(pos.below().north()), Vec3.atLowerCornerOf(pos.west()));
        } else if (state.getValue(SIDE).equals(GiantCropSide.swd)) {
            //good
            return new AABB(Vec3.atLowerCornerOf(pos.east().north()),Vec3.atLowerCornerOf(pos.above()));
        } else if (state.getValue(SIDE).equals(GiantCropSide.swu)) {
            return new AABB(Vec3.atLowerCornerOf(pos.east().below().north()),Vec3.atLowerCornerOf(pos));
        }
        return new AABB(pos);
    }

    private static List<BlockPos> getBlockPositions(AABB box) {
        List<BlockPos> positions = new ArrayList<>();
        int minX = (int) Math.floor(box.minX);
        int minY = (int) Math.floor(box.minY);
        int minZ = (int) Math.floor(box.minZ);
        int maxX = (int) Math.floor(box.maxX);
        int maxY = (int) Math.floor(box.maxY);
        int maxZ = (int) Math.floor(box.maxZ);
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    positions.add(new BlockPos(x, y, z));
                }
            }
        }
        return positions;
    }

    protected ItemLike getSeedsItem() {
        return Items.WHEAT_SEEDS;
    }

    //? if >= 26.2 {
    /*@Override
    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
    *///? } else {
    @Override
    public ItemStack getCloneItemStack(BlockState state, net.minecraft.world.phys.HitResult target, LevelReader world, BlockPos pos, net.minecraft.world.entity.player.Player player) {
    //?}
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SIDE);
    }
}
