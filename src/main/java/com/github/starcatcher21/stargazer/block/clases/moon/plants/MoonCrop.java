package com.github.starcatcher21.stargazer.block.clases.moon.plants;

import com.github.starcatcher21.stargazer.block.clases.moon.MoonFarmland;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MoonCrop
        extends VegetationBlock
        implements BonemealableBlock {
    public static final MapCodec<MoonCrop> CODEC = MoonCrop.simpleCodec(MoonCrop::new);
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape[] SHAPES_BY_AGE = Block.boxes(7, age -> Block.column(16.0, 0.0, 2 + age * 2));

    @Nullable
    public Block giantCrop = null;

    public MapCodec<? extends MoonCrop> codec() {
        return CODEC;
    }


    public MoonCrop(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(this.getAgeProperty(), 0));
    }

    public MoonCrop(BlockBehaviour.Properties settings, @Nullable  Block crop) {
        super(settings);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(this.getAgeProperty(), 0));
        this.giantCrop = crop;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPES_BY_AGE[this.getAge(state)];
    }


    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(Blocks.FARMLAND);
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 7;
    }

    public int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return (BlockState)this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    public final boolean isMature(BlockState state) {
        return this.getAge(state) >= this.getMaxAge();
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !this.isMature(state);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        float f;
        int i;
        if (world.getRawBrightness(pos, 0) >= 9 && (i = this.getAge(state)) < this.getMaxAge() && random.nextInt((int)(25.0f / (f = MoonCrop.getAvailableMoisture(this, world, pos))) + 1) == 0) {
            world.setBlock(pos, this.withAge(i + 1), Block.UPDATE_CLIENTS);
        }
    }

    public void applyGrowth(Level world, BlockPos pos, BlockState state) {
        int i = Math.min(this.getMaxAge(), this.getAge(state) + this.getGrowthAmount(world));
        world.setBlock(pos, this.withAge(i), Block.UPDATE_CLIENTS);

        if (state.getValue(AGE).equals(this.getMaxAge()) && giantCrop != null) {
            List<Boolean> list = new ArrayList<>();
            for (BlockPos blockPos : BlockPos.betweenClosed(pos, pos.offset(1, 0, 1))) {
                if (world.getBlockState(blockPos).equals(this.defaultBlockState().setValue(AGE, this.getMaxAge())) && world.getBlockState(blockPos.above()).getBlock().equals(Blocks.AIR)) {
                    list.add(true);
                }
            }
            if (list.size() == 4) {
                GiantDragonCarrot.place(world, pos, giantCrop.defaultBlockState().setValue(GiantCrop.SIDE, GiantCropSide.nwd));
                return;
            } else {
                list = new ArrayList<>();
            }
            for (BlockPos blockPos : BlockPos.betweenClosed(pos, pos.offset(-1, 0, -1))) {
                if (world.getBlockState(blockPos).equals(this.defaultBlockState().setValue(AGE, this.getMaxAge())) && world.getBlockState(blockPos.above()).getBlock().equals(Blocks.AIR)) {
                    list.add(true);
                }
            }
            if (list.size() == 4) {
                GiantCrop.place(world, pos, giantCrop.defaultBlockState().setValue(GiantCrop.SIDE, GiantCropSide.sed));
                return;
            } else {
                list = new ArrayList<>();
            }
            for (BlockPos blockPos : BlockPos.betweenClosed(pos, pos.offset(1, 0, -1))) {
                if (world.getBlockState(blockPos).equals(this.defaultBlockState().setValue(AGE, this.getMaxAge())) && world.getBlockState(blockPos.above()).getBlock().equals(Blocks.AIR)) {
                    list.add(true);
                }
            }
            if (list.size() == 4) {
                GiantCrop.place(world, pos, giantCrop.defaultBlockState().setValue(GiantCrop.SIDE, GiantCropSide.swd));
                return;
            } else {
                list = new ArrayList<>();
            }
            for (BlockPos blockPos : BlockPos.betweenClosed(pos, pos.offset(-1, 0, 1))) {
                if (world.getBlockState(blockPos).equals(this.defaultBlockState().setValue(AGE, this.getMaxAge())) && world.getBlockState(blockPos.above()).getBlock().equals(Blocks.AIR)) {
                    list.add(true);
                }
            }
            if (list.size() == 4) {
                GiantCrop.place(world, pos, giantCrop.defaultBlockState().setValue(GiantCrop.SIDE, GiantCropSide.ned));
            }
        }
    }

    protected int getGrowthAmount(Level world) {
        return Mth.nextInt(world.random, 2, 5);
    }

    protected static float getAvailableMoisture(Block block, Level world, BlockPos pos) {
        boolean bl2;
        float f = 1.0f;
        BlockPos blockPos = pos.below();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float g = 0.0f;
                BlockState blockState = world.getBlockState(blockPos.offset(i, 0, j));
                if (blockState.is(MoonBlocks.MOON_FARMLAND)) {
                    g = 1.0f;
                    if (blockState.getValue(MoonFarmland.MOISTURE)) {
                        g = 3.0f;
                    }
                }
                if (i != 0 || j != 0) {
                    g /= 4.0f;
                }
                f += g;
            }
        }
        BlockPos blockPos2 = pos.north();
        BlockPos blockPos3 = pos.south();
        BlockPos blockPos4 = pos.west();
        BlockPos blockPos5 = pos.east();
        boolean bl = world.getBlockState(blockPos4).is(block) || world.getBlockState(blockPos5).is(block);
        boolean bl3 = bl2 = world.getBlockState(blockPos2).is(block) || world.getBlockState(blockPos3).is(block);
        if (bl && bl2) {
            f /= 2.0f;
        } else {
            boolean bl32;
            boolean bl4 = bl32 = world.getBlockState(blockPos4.north()).is(block) || world.getBlockState(blockPos5.north()).is(block) || world.getBlockState(blockPos5.south()).is(block) || world.getBlockState(blockPos4.south()).is(block);
            if (bl32) {
                f /= 2.0f;
            }
        }
        return f;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).is(MoonBlocks.MOON_FARMLAND);
    }

    protected static boolean hasEnoughLightAt(LevelReader world, BlockPos pos) {
        return world.getRawBrightness(pos, 0) >= 8;
    }

    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl) {
        if (world instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel)world;
            if (entity instanceof Ravager && serverWorld.getGameRules().get(net.minecraft.world.level.gamerules.GameRules.MOB_GRIEFING)) {
                serverWorld.destroyBlock(pos, true, entity);
            }
        }
        super.entityInside(state, world, pos, entity, handler, bl);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return !this.isMature(state);
    }

    @Override
    public boolean isBonemealSuccess(Level world, net.minecraft.util.RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, net.minecraft.util.RandomSource random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}