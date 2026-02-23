package com.github.starcatcher21.stargazer.worldgen.features.trees;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Tree {
    public final Boolean ROTATO;
    public final String name;
    public final Set<BlockPos> logs = new ObjectArraySet<>();
    public final Set<BlockPos> leaves = new ObjectArraySet<>();
    public final Set<BlockPos> fruits = new ObjectArraySet<>();
    public final Set<Block> replacable = new ObjectArraySet<>();
    public final Set<BlockState> log = new ObjectArraySet<>();
    public final Set<BlockState> leave = new ObjectArraySet<>();
    public final Set<BlockState> fruit = new ObjectArraySet<>();
    public int fruitchange = 0;
    private static final Random random = new Random();

    public Tree(Boolean rotatable, String name) {
        this.ROTATO = rotatable;
        this.name = name;
        this.log.add(Blocks.AIR.getDefaultState());
        this.leave.add(Blocks.AIR.getDefaultState());
    }
    public Tree(Boolean rotatable, String name, BlockState log, BlockState leave) {
        this.ROTATO = rotatable;
        this.name = name;
        this.log.add(log);
        this.leave.add(leave);
    }
    public Tree(Boolean rotatable, String name, Set<BlockState> log, Set<BlockState> leave) {
        this.ROTATO = rotatable;
        this.name = name;
        this.log.addAll(log);
        this.leave.addAll(leave);
    }

    public void addLogPos(int X, int Y, int Z) {
        this.logs.add(new BlockPos(X, Y, Z));
    }
    public void addLeavesPos(int X, int Y, int Z) {
        this.leaves.add(new BlockPos(X, Y, Z));
    }
    public void addFruitsPos(int X, int Y, int Z) {
        this.fruits.add(new BlockPos(X, Y, Z));
    }
    public void addLogPos(BlockPos pos) {
        this.logs.add(pos);
    }
    public void addLeavesPos(BlockPos pos) {
        this.leaves.add(pos);
    }
    public void addFruitsPos(BlockPos pos) {
        this.fruits.add(pos);
    }
    public void addLogPos(Set<BlockPos> list) {
        this.logs.addAll(list);
    }
    public void addLeavesPos(Set<BlockPos> list) {
        this.leaves.addAll(list);
    }
    public void addFruitsPos(Set<BlockPos> list) {
        this.fruits.addAll(list);
    }
    public void addReplacableBlock(Block block) {
        this.replacable.add(block);
    }
    public void addReplacableBlock(Set<Block> block) {
        this.replacable.addAll(block);
    }
    public void addReplacableBlock(TagKey<Block> blocks) {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Registries.BLOCK.getOptional(blocks).ifPresent(entryList -> {
                for (var entry : entryList) {
                    this.replacable.add(entry.value());
                }
            });
            System.out.println("Successfully populated set with " + this.replacable.size() + " blocks.");
        });    }
    public void addLog(BlockState block) {
        this.log.add(block);
    }
    public void clearLog() {
        this.log.clear();
    }
    public void addLeave(BlockState block) {
        this.leave.add(block);
    }
    public void clearLeave() {
        this.leave.clear();
    }
    public void addFruit(BlockState block) {
        this.fruit.add(block);
    }
    public void clearFruit() {
        this.fruit.clear();
    }
    public void addFruits(Set<BlockState> list) {
        this.fruit.addAll(list);
    }
    public void removeFruitPos(Set<BlockPos> list) {
        this.fruits.removeAll(list);
    }
    public void setFruitChange(int change) {
        this.fruitchange = change;
    }

    public Boolean canGrow(StructureWorldAccess world, BlockPos base) {
        for (BlockPos pos : logs) {
            if (!(pos.equals(new BlockPos(0, 0, 0 )))) {
                if (!(checkBlocks(world, base.add(pos), false))) {
                    return false;
                }
            }
        }
        for (BlockPos pos : leaves) {
            if (!(pos.equals(new BlockPos(0, 0, 0 )))) {
                if (!(checkBlocks(world, base.add(pos), false))) {
                    return false;
                }
            }
        }
        if (!fruit.isEmpty() && !fruits.isEmpty()) {
            for (BlockPos pos : fruits) {
                if (!(pos.equals(new BlockPos(0, 0, 0 )))) {
                    if (!(checkBlocks(world, base.add(pos), true))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean Grow(StructureWorldAccess world, BlockPos base) {
        if (canGrow(world, base)) {
            BlockPos.Mutable mutable = base.mutableCopy();
            for (int i = 0; i < logs.size(); i ++) {
                BlockPos pos = logs.stream().toList().get(i);
                BlockPos prev;
                if (i - 1 >= 0) {
                    prev = logs.stream().toList().get(i-1);
                } else {
                    prev = logs.stream().toList().get(i+1);
                }
                BlockState newLog = log.stream().toList().get(random.nextInt(log.size()));
                if (newLog.getProperties().contains(Properties.AXIS)) {
                    if (Math.abs(pos.getZ()) > Math.abs(pos.getX())) {
                        newLog = newLog.with(Properties.AXIS, Direction.Axis.Z);
                    }
                    if (Math.abs(pos.getX()) > Math.abs(pos.getZ())) {
                        newLog = newLog.with(Properties.AXIS, Direction.Axis.X);
                    }
                    if (prev.getX() == pos.getX() && prev.getZ() == pos.getZ()) {
                        newLog = newLog.with(Properties.AXIS, Direction.Axis.Y);
                    }
                    if (Math.abs(pos.getZ()) == Math.abs(pos.getX()) || logs.contains(pos.up(1)) || logs.contains(pos.down(1))) {
                        newLog = newLog.with(Properties.AXIS, Direction.Axis.Y);
                    }
                }
                if (world.isOutOfHeightLimit(mutable.add(pos))) continue;
                if (world.isValidForSetBlock(mutable.add(pos))) {
                    world.setBlockState(mutable.add(pos), newLog, Block.NOTIFY_LISTENERS | Block.FORCE_STATE);
                }
            }
            for (BlockPos pos : leaves) {
                BlockState leav =  leave.stream().toList().get(random.nextInt(leave.size()));
                if (leav.contains(MushroomBlock.WEST) && leav.contains(MushroomBlock.EAST) && leav.contains(MushroomBlock.NORTH) && leav.contains(MushroomBlock.SOUTH) && leav.contains(MushroomBlock.UP)) {
                    List<Block> logblock = log.stream().map((block) -> block.getBlock()).toList();
                    int search = 3;
                    BlockPos absoluteLeafPos = base.add(pos);
                    boolean foundLogNorth = false;
                    boolean foundLogSouth = false;
                    boolean foundLogEast = false;
                    boolean foundLogWest = false;

                    for (int xOffset = -search; xOffset <= search; xOffset++) {
                        for (int zOffset = -search; zOffset <= search; zOffset++) {
                            if (xOffset == 0 && zOffset == 0) {
                                continue;
                            }

                            BlockPos checkPos = absoluteLeafPos.add(xOffset, 0, zOffset);

                            if (logblock.contains(world.getBlockState(checkPos).getBlock())) {
                                if (zOffset < 0) {
                                    foundLogNorth = true;
                                }
                                if (zOffset > 0) {
                                    foundLogSouth = true;
                                }
                                if (xOffset > 0) {
                                    foundLogEast = true;
                                }
                                if (xOffset < 0) {
                                    foundLogWest = true;
                                }
                            }
                        }
                    }
                    boolean north = !foundLogNorth;
                    boolean south = !foundLogSouth;
                    boolean east = !foundLogEast;
                    boolean west = !foundLogWest;
                    leav = leav.with(MushroomBlock.DOWN, false)
                            .with(MushroomBlock.UP, true)
                            .with(MushroomBlock.WEST, west)
                            .with(MushroomBlock.EAST, east)
                            .with(MushroomBlock.NORTH, north)
                            .with(MushroomBlock.SOUTH, south);
                }
                if (leav.contains(Properties.DISTANCE_1_7)) {
                    leav = leav.with(Properties.DISTANCE_1_7, 1);
                }
                if (world.isOutOfHeightLimit(mutable.add(pos))) continue;
                if (world.isValidForSetBlock(mutable.add(pos))) {
                    world.setBlockState(mutable.add(pos), leav, Block.NOTIFY_LISTENERS | Block.FORCE_STATE);
                }
            }
            if (!fruit.isEmpty()) {
                for (BlockPos pos : fruits) {
                    BlockState frut = fruit.stream().toList().get(random.nextInt(fruit.size()));
                    if (random.nextInt(100) <= this.fruitchange) {
                        if (world.isOutOfHeightLimit(mutable.add(pos))) continue;
                        if (world.isValidForSetBlock(mutable.add(pos))) {
                            world.setBlockState(mutable.add(pos), frut, Block.NOTIFY_LISTENERS | Block.FORCE_STATE);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkBlocks(StructureWorldAccess world, BlockPos pos, boolean isFruit) {
        for (Block block : replacable) {
            if (world.getBlockState(pos).getBlock().equals(block)) {
                if (!(isFruit && block.getDefaultState().isIn(BlockTags.LEAVES))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Tree offset(Tree tree, Direction dir, int offset) {
        Tree newTree = new Tree(true, tree.name+"Offset");
        for (BlockPos pos : tree.fruits) {
            newTree.addFruitsPos(pos.offset(dir, offset));
        }
        Set<BlockPos> remFruts = new ObjectArraySet<>();
        for (BlockPos pos : tree.logs) {
            for (BlockPos frutpos : newTree.fruits) {
                if (pos.offset(dir, offset).equals(frutpos)) {
                    remFruts.add(frutpos);
                }
            }
            newTree.addLogPos(pos.offset(dir, offset));
        }
        for (BlockPos pos : tree.leaves) {
            for (BlockPos frutpos : newTree.fruits) {
                if (pos.offset(dir, offset).equals(frutpos)) {
                    remFruts.add(frutpos);
                }
            }
            newTree.addLeavesPos(pos.offset(dir, offset));
        }
        newTree.removeFruitPos(remFruts);
        return newTree;
    }

    public static Tree genBranch(int height) {
        Tree branch = new Tree(false, "branch");
        for (int i = 0; i < height; i++) {
            branch.addLogPos(0,i,0);
        }
        return branch;
    }

    public static void addBranch(Tree treeB, Tree branchB, Direction dir) {
        Tree rotated = DirectionalTree.getFromNorth(branchB, dir);
        treeB.addLogPos(rotated.logs);
        treeB.addLeavesPos(rotated.leaves);
        treeB.addFruitsPos(rotated.fruits);
    }

    public String countLogs() {
        return "" + logs.size();
    }

    public String countLeaves() {
        return "" + leaves.size();
    }

    public String countFruits() {
        return "" + fruits.size();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                ", logs amount=" + countLogs() +
                ", leaves amount=" + countLeaves() +
                ", fruits amount=" + countFruits() +
                ", logs=" + logs +
                ", leaves=" + leaves +
                ", replacable=" + replacable +
                ", log=" + log +
                ", leave=" + leave +
                ", fruit=" + fruit +
                ", fruits=" + fruits +
                '}';
    }

}