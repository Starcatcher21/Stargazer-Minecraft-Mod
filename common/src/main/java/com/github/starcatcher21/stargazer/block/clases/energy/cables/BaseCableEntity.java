package com.github.starcatcher21.stargazer.block.clases.energy.cables;

import com.github.starcatcher21.stargazer.energy.EnergyHooks;
import com.github.starcatcher21.stargazer.energy.SimpleModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseCableEntity extends BlockEntity {
    public final SimpleModEnergyStorage energyStorage;

    public BaseCableEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, long capacity, long maxReceive, long maxExtract) {
        super(type, pos, state);
        this.energyStorage = new SimpleModEnergyStorage(capacity, maxReceive, maxExtract, this::setChanged);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BaseCableEntity blockEntity) {
        if (world.isClientSide() || blockEntity.energyStorage.getAmount() <= 0) return;

        for (Direction direction : Direction.values()) {
            if (blockEntity.energyStorage.getAmount() <= 0) break;

            BlockPos targetPos = pos.relative(direction);
            
            // Check if target is a neighbor cable of the same type for energy balancing
            long maxToMove;
            if (world.getBlockEntity(targetPos) instanceof BaseCableEntity neighborCable) {
                long currentEnergy = blockEntity.energyStorage.getAmount();
                long neighborEnergy = neighborCable.energyStorage.getAmount();

                if (currentEnergy <= neighborEnergy) {
                    continue;
                }

                long difference = currentEnergy - neighborEnergy;
                maxToMove = difference / 2;
            } else {
                maxToMove = Math.min(blockEntity.energyStorage.getAmount(), blockEntity.energyStorage.extractEnergy(Long.MAX_VALUE, true));
            }

            if (maxToMove <= 0) continue;

            // Push energy cross-platform
            EnergyHooks.pushEnergy(world, pos, direction, blockEntity.energyStorage, maxToMove);
        }
    }
}