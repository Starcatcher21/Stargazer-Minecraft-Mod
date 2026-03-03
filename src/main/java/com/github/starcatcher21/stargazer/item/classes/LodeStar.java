package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.clases.teleporter.CopperTeleporter;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class LodeStar extends Item {
    public LodeStar(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos root = context.getBlockPos();
        if (isProperTeleporter(world, root)) {
            CopperTeleporter.portalPlace(world, root, false, false);
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(root.getX(), root.getY()+1, root.getZ());
            world.spawnEntity(lightning);
            if (context.getPlayer() instanceof ServerPlayerEntity spe) {
                Criterias.cosmicPortal.trigger(spe);
            }
            return ActionResult.SUCCESS;
        }
        if (world.getBlockState(root).isIn(CustomTags.COPPER_BLOCKS)) {
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    public static Boolean isProperTeleporter(World world, BlockPos pos) {
        if (!(world.getBlockState(pos).getBlock().equals(Blocks.CUT_COPPER) || world.getBlockState(pos).getBlock().equals(Blocks.WAXED_CUT_COPPER))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.NORTH, 1)).equals(Blocks.CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH)) || world.getBlockState(pos.offset(Direction.NORTH, 1)).equals(Blocks.WAXED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.SOUTH, 1)).equals(Blocks.CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH)) || world.getBlockState(pos.offset(Direction.SOUTH, 1)).equals(Blocks.WAXED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.WEST, 1)).equals(Blocks.CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST)) || world.getBlockState(pos.offset(Direction.WEST, 1)).equals(Blocks.WAXED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.EAST, 1)).equals(Blocks.CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST)) || world.getBlockState(pos.offset(Direction.EAST, 1)).equals(Blocks.WAXED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.EAST, 1)).getBlock().equals(Blocks.CHISELED_COPPER) || world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.EAST, 1)).getBlock().equals(Blocks.WAXED_CHISELED_COPPER))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.WEST, 1)).getBlock().equals(Blocks.CHISELED_COPPER) || world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.WEST, 1)).getBlock().equals(Blocks.WAXED_CHISELED_COPPER))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.EAST, 1)).getBlock().equals(Blocks.CHISELED_COPPER) || world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.EAST, 1)).getBlock().equals(Blocks.WAXED_CHISELED_COPPER))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.WEST, 1)).getBlock().equals(Blocks.CHISELED_COPPER) || world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.WEST, 1)).getBlock().equals(Blocks.WAXED_CHISELED_COPPER))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.EAST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.CUT_COPPER) || world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.EAST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.WAXED_CUT_COPPER))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.WEST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.CUT_COPPER) || world.getBlockState(pos.offset(Direction.NORTH, 1).offset(Direction.WEST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.WAXED_CUT_COPPER))) {
            return false;
        }
        if (!(world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.EAST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.CUT_COPPER) || world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.EAST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.WAXED_CUT_COPPER))) {
            return false;
        }
        return world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.WEST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.CUT_COPPER) || world.getBlockState(pos.offset(Direction.SOUTH, 1).offset(Direction.WEST, 1).offset(Direction.UP, 1)).getBlock().equals(Blocks.WAXED_CUT_COPPER);
    }
}
