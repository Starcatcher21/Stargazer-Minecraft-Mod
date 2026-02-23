package com.github.starcatcher21.stargazer;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class Helpers {

    public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureOf(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Stargazer.MOD_ID, id));
    }
    public static VoxelShape getVox(LivingEntity entity, BlockPos pos) {
        Box entBox = entity.getBoundingBox();
        double enminX = entBox.minX - pos.getX();
        double enminY = entBox.minY - pos.getY();
        double enminZ = entBox.minZ - pos.getZ();
        double enmaxX = entBox.maxX - pos.getX();
        double enmaxY = entBox.maxY - pos.getY();
        double enmaxZ = entBox.maxZ - pos.getZ();
        return VoxelShapes.cuboid(enminX, enminY, enminZ, enmaxX, enmaxY, enmaxZ);
    }
    public static boolean isIntersect(World world, LivingEntity entity, BlockState state, BlockPos pos) {
        BlockView bw = (BlockView) world;
        VoxelShape entVox = Helpers.getVox(entity, pos);
        VoxelShape blockVox = state.getOutlineShape(bw, pos);

        Box bBox = blockVox.getBoundingBox();
        Box eBox = entVox.getBoundingBox();
        return bBox.intersects(eBox);
    }
    public static boolean isIntersectSolid(World world, LivingEntity entity, BlockState state, BlockPos pos) {
        BlockView bw = (BlockView) world;
        VoxelShape entVox = Helpers.getVox(entity, pos);
        VoxelShape blockVox = state.getOutlineShape(bw, pos);

        Box bhBox = blockVox.getBoundingBox();
        Box bBox = VoxelShapes.cuboid(bhBox.minX - 0.1, bhBox.minY - 0.1, bhBox.minZ - 0.1, bhBox.maxX + 0.1, bhBox.maxY + 0.1, bhBox.maxZ + 0.1).getBoundingBox();
        Box eBox = entVox.getBoundingBox();
        return bBox.intersects(eBox);
    }
    public static boolean isIntersectCollision(World world, LivingEntity entity, BlockState state, BlockPos pos) {
        BlockView bv = (BlockView) world;
        VoxelShape entVox = Helpers.getVox(entity, pos);
        VoxelShape blockVox = state.getCollisionShape(bv, pos);

        Box bBox = blockVox.getBoundingBox();
        Box eBox = entVox.getBoundingBox();
        return bBox.intersects(eBox);
    }
    public static boolean isIntersectSolidCollision(World world, LivingEntity entity, BlockState state, BlockPos pos) {
        BlockView bw = (BlockView) world;
        VoxelShape entVox = Helpers.getVox(entity, pos);
        VoxelShape blockVox = state.getCollisionShape(bw, pos);

        Box bhBox = blockVox.getBoundingBox();
        Box bBox = VoxelShapes.cuboid(bhBox.minX - 0.1, bhBox.minY - 0.1, bhBox.minZ - 0.1, bhBox.maxX + 0.1, bhBox.maxY + 0.1, bhBox.maxZ + 0.1).getBoundingBox();
        Box eBox = entVox.getBoundingBox();
        return bBox.intersects(eBox);
    }
    public static boolean isIntersectCustom(LivingEntity entity, BlockPos pos, Box bBox) {
        VoxelShape entVox = Helpers.getVox(entity, pos);

        Box eBox = entVox.getBoundingBox();
        return bBox.intersects(eBox);
    }
    public static VoxelShape FacingAll(Direction dir, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return switch (dir) {
            case DOWN -> VoxelShapes.cuboid(1-maxX, 1-maxY, 1-maxZ, 1-minX, 1-minY, 1-minZ);
            case EAST -> VoxelShapes.cuboid(minY, minX, minZ, maxY, maxX, maxZ);
            case WEST -> VoxelShapes.cuboid(1-maxY, 1-maxX, 1-maxZ, 1-minY, 1-minX, 1-minZ);
            case NORTH -> VoxelShapes.cuboid(1-maxZ, 1-maxX, 1-maxY, 1-minZ, 1-minX, 1-minY);
            case SOUTH -> VoxelShapes.cuboid(minZ, minX, minY, maxZ, maxX, maxY);
            default ->  VoxelShapes.cuboid(minX, minY, minZ, maxX, maxY, maxZ);
        };
    }
    public static VoxelShape FacingHorizontal(Direction dir, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, boolean lowZ) {
        if (!lowZ) {
            return switch (dir) {
                case EAST -> VoxelShapes.cuboid(1 - maxZ, 1 - maxY, 1 - maxX, 1 - minZ, 1 - minY, 1 - minX);
                case WEST -> VoxelShapes.cuboid(minZ, minY, minX, maxZ, maxY, maxX);
                case SOUTH -> VoxelShapes.cuboid(1 - maxX, 1 - maxY, 1 - maxZ, 1 - minX, 1 - minY, 1 - minZ);
                default -> VoxelShapes.cuboid(minX, minY, minZ, maxX, maxY, maxZ);
            };
        } else {
            return switch (dir) {
                case EAST -> VoxelShapes.cuboid(1 - maxZ, minY, 1 - maxX, 1 - minZ,  maxY, 1 - minX);
                case WEST -> VoxelShapes.cuboid(minZ, minY, minX, maxZ, maxY, maxX);
                case SOUTH -> VoxelShapes.cuboid(1 - maxX, minY, 1 - maxZ, 1 - minX, maxY, 1 - minZ);
                default -> VoxelShapes.cuboid(minX, minY, minZ, maxX, maxY, maxZ);
            };
        }
    }
    public static boolean isColliding(Block targetBlock, BlockView world, BlockPos pos, Direction dir) {
        return switch (dir) {
            case NORTH -> world.getBlockState(pos.north(1)).getBlock() == targetBlock;
            case SOUTH -> world.getBlockState(pos.south(1)).getBlock() == targetBlock;
            case WEST -> world.getBlockState(pos.west(1)).getBlock() == targetBlock;
            case EAST -> world.getBlockState(pos.east(1)).getBlock() == targetBlock;
            case UP -> world.getBlockState(pos.up(1)).getBlock() == targetBlock;
            case DOWN -> world.getBlockState(pos.down(1)).getBlock() == targetBlock;
        };
    }
    public static boolean isCollidingAny(Block targetBlock, BlockView world, BlockPos pos, ImmutableList<Direction> list) {
        for (Direction direction : list) {
            if (isColliding(targetBlock, world, pos, direction)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static Direction getCollidingDirection(Block targetBlock, BlockView world, BlockPos pos, ImmutableList<Direction> list) {
        for (Direction direction : list) {
            if (isColliding(targetBlock, world, pos, direction)) {
                return direction;
            }
        }
        return null;
    }

    @Nullable
    public static BlockPos getCollidingPosition(Block targetBlock, BlockView world, BlockPos pos, ImmutableList<Direction> list) {
        for (Direction direction : list) {
            if (isColliding(targetBlock, world, pos, direction)) {
                return switch (direction) {
                    case NORTH -> pos.north(1);
                    case SOUTH -> pos.south(1);
                    case WEST -> pos.west(1);
                    case EAST -> pos.east(1);
                    case UP -> pos.up(1);
                    case DOWN -> pos.down(1);
                };
            }
        }
        return null;
    }
}
