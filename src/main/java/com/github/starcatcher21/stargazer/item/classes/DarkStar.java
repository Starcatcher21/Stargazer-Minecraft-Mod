package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.block.clases.teleporter.DarkTeleporter;
import com.github.starcatcher21.stargazer.block.clases.teleporter.EndTeleporter;
import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DarkStar extends Item {
    public DarkStar(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos root = context.getClickedPos();
        if (isProperTeleporter(world, root)) {
            DarkTeleporter.portalPlace(world, root, false, false);
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightning.setPosRaw(root.getX(), root.getY()+1, root.getZ());
            world.addFreshEntity(lightning);
            if (context.getPlayer() instanceof ServerPlayer spe) {
                Criterias.cosmicPortal.trigger(spe);
            }
            return InteractionResult.SUCCESS;
        }
        if (isProperEndTeleporter(world, root)) {
            EndTeleporter.portalPlace(world, root, false, false);
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightning.setPosRaw(root.getX(), root.getY()+1, root.getZ());
            world.addFreshEntity(lightning);
            if (context.getPlayer() instanceof ServerPlayer spe) {
                Criterias.cosmicPortal.trigger(spe);
            }
            return InteractionResult.SUCCESS;
        }
        if (world.getBlockState(root).equals(Blocks.OBSIDIAN.defaultBlockState()) || world.getBlockState(root).equals(Blocks.END_STONE_BRICKS.defaultBlockState())) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public static Boolean isProperTeleporter(Level world, BlockPos pos) {
        if (!(world.getBlockState(pos).getBlock().equals(Blocks.OBSIDIAN))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1)).equals(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1)).equals(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.WEST, 1)).equals(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.EAST, 1)).equals(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1)).getBlock().equals(Chess.BLACK_BRICKS))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.WEST, 1)).getBlock().equals(Chess.BLACK_BRICKS))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1)).getBlock().equals(Chess.BLACK_BRICKS))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1)).getBlock().equals(Chess.BLACK_BRICKS))) {
            return false;
        }
        return true;
    }
    public static Boolean isProperEndTeleporter(Level world, BlockPos pos) {
        if (!(world.getBlockState(pos).getBlock().equals(Blocks.END_STONE_BRICKS))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1)).equals(Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1)).equals(Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.WEST, 1)).equals(Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.EAST, 1)).equals(Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1)).getBlock().equals(Chess.WHITE_BRICKS))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.WEST, 1)).getBlock().equals(Chess.WHITE_BRICKS))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1)).getBlock().equals(Chess.WHITE_BRICKS))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1)).getBlock().equals(Chess.WHITE_BRICKS))) {
            return false;
        }
        return true;
    }
}
