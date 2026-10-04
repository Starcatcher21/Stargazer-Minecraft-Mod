package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.clases.teleporter.CopperTeleporter;
import com.github.starcatcher21.stargazer.block.clases.teleporter.RedTeleporter;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
//? if >= 26.2 {
/*import net.minecraft.world.entity.EntityTypes;
*///? }
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class LodeStar extends Item {
    public LodeStar(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos root = context.getClickedPos();
        if (isProperTeleporter(world, root)) {
            CopperTeleporter.portalPlace(world, root, false, false);
            LightningBolt lightning = new LightningBolt(
                    //? if >= 26.2 {
                    /*EntityTypes
                    *///? } else {
                    EntityType
                    //? }
                            .LIGHTNING_BOLT, world);
            lightning.setPosRaw(root.getX(), root.getY()+1, root.getZ());
            world.addFreshEntity(lightning);
            if (context.getPlayer() instanceof ServerPlayer spe) {
                //? if >= 26.2 {
                /*Criterias.COSMIC_PORTAL.listen(crit -> crit.trigger(spe));
                *///? } else {
                Criterias.COSMIC_PORTAL.trigger(spe);
                //? }
            }
            return InteractionResult.SUCCESS;
        }
        if (isProperRedTeleporter(world, root)) {
            RedTeleporter.portalPlace(world, root, false, false);
            LightningBolt lightning = new LightningBolt(
                    //? if >= 26.2 {
                    /*EntityTypes
                    *///? } else {
                    EntityType
                    //? }
                            .LIGHTNING_BOLT, world);
            lightning.setPosRaw(root.getX(), root.getY()+1, root.getZ());
            world.addFreshEntity(lightning);
            return InteractionResult.SUCCESS;
        }
        if (world.getBlockState(root).is(CustomTags.COPPER_BLOCKS)) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public static Boolean isProperRedTeleporter(Level world, BlockPos pos) {
        if (!(world.getBlockState(pos).getBlock().equals(RedOrbBlocks.POLISHED_RED_ROCK.get()))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1)).equals(RedOrbBlocks.RED_ROCK_STAIRS.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1)).equals(RedOrbBlocks.RED_ROCK_STAIRS.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.WEST, 1)).equals(RedOrbBlocks.RED_ROCK_STAIRS.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)))) {
            return false;
        }
        return world.getBlockState(pos.relative(Direction.EAST, 1)).equals(RedOrbBlocks.RED_ROCK_STAIRS.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST));
    }
    public static Boolean isProperTeleporter(Level world, BlockPos pos) {
        //? if >= 26.2 {
        /*var cut = Blocks.CUT_COPPER.weathering().unaffected();
        var cut_w = Blocks.CUT_COPPER.waxed().unaffected();
        var cut_s = Blocks.CUT_COPPER_STAIRS.weathering().unaffected();
        var cut_sw = Blocks.CUT_COPPER_STAIRS.waxed().unaffected();
        var chis = Blocks.CHISELED_COPPER.weathering().unaffected();
        var chis_w = Blocks.CHISELED_COPPER.waxed().unaffected();
        *///? } else {
        var cut = Blocks.CUT_COPPER;
        var cut_w = Blocks.WAXED_CUT_COPPER;
        var cut_s = Blocks.CUT_COPPER_STAIRS;
        var cut_sw = Blocks.WAXED_CUT_COPPER_STAIRS;
        var chis = Blocks.CHISELED_COPPER;
        var chis_w = Blocks.WAXED_CHISELED_COPPER;
        //? }

        if (!(world.getBlockState(pos).getBlock().equals(cut) || world.getBlockState(pos).getBlock().equals(cut_w))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1)).equals(cut_s.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)) || world.getBlockState(pos.relative(Direction.NORTH, 1)).equals(cut_sw.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1)).equals(cut_s.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)) || world.getBlockState(pos.relative(Direction.SOUTH, 1)).equals(cut_sw.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.WEST, 1)).equals(cut_s.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)) || world.getBlockState(pos.relative(Direction.WEST, 1)).equals(cut_sw.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.EAST, 1)).equals(cut_s.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)) || world.getBlockState(pos.relative(Direction.EAST, 1)).equals(cut_sw.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1)).getBlock().equals(chis) || world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1)).getBlock().equals(chis_w))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.WEST, 1)).getBlock().equals(chis) || world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.WEST, 1)).getBlock().equals(chis_w))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1)).getBlock().equals(chis) || world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1)).getBlock().equals(chis_w))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1)).getBlock().equals(chis) || world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1)).getBlock().equals(chis_w))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1).relative(Direction.UP, 1)).getBlock().equals(cut) || world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1).relative(Direction.UP, 1)).getBlock().equals(cut_w))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.WEST, 1).relative(Direction.UP, 1)).getBlock().equals(cut) || world.getBlockState(pos.relative(Direction.NORTH, 1).relative(Direction.WEST, 1).relative(Direction.UP, 1)).getBlock().equals(cut_w))) {
            return false;
        }
        if (!(world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1).relative(Direction.UP, 1)).getBlock().equals(cut) || world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1).relative(Direction.UP, 1)).getBlock().equals(cut_w))) {
            return false;
        }
        return world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1).relative(Direction.UP, 1)).getBlock().equals(cut) || world.getBlockState(pos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1).relative(Direction.UP, 1)).getBlock().equals(cut_w);
    }}
