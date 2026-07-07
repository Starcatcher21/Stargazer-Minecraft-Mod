package com.github.starcatcher21.stargazer.block.clases.energy.cables;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class RedCableEntity extends BlockEntity implements EnergyStorage {
	public RedCableEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.RED_CABLE, pos, state);
	}

	public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10000, 10000, 10000) {
		@Override
		protected void onFinalCommit() {
			setChanged();
		}
	};

	@Override
	protected void loadAdditional(ValueInput nbt) {
		super.loadAdditional(nbt);
		energyStorage.amount = nbt.getLongOr("energy", 0);
	}

	@Override
	protected void saveAdditional(ValueOutput nbt) {
		super.saveAdditional(nbt);
		nbt.putLong("energy", energyStorage.amount);
	}

	@Override
	public long insert(long maxAmount, TransactionContext transaction) {
		return energyStorage.insert(maxAmount, transaction);
	}

	@Override
	public long extract(long maxAmount, TransactionContext transaction) {
		return energyStorage.extract(maxAmount, transaction);
	}

	@Override
	public long getAmount() {
		return energyStorage.getAmount();
	}

	@Override
	public long getCapacity() {
		return energyStorage.getCapacity();
	}

	@Override
	public boolean supportsInsertion() {
		return energyStorage.supportsInsertion();
	}

	@Override
	public boolean supportsExtraction() {
		return energyStorage.supportsExtraction();
	}

	public static void tick(Level world, BlockPos pos, BlockState state, RedCableEntity blockEntity) {
		if (world.isClientSide() || blockEntity.energyStorage.getAmount() <= 0) return;

		for (Direction direction : Direction.values()) {
			if (blockEntity.energyStorage.getAmount() <= 0) break;

			BlockPos targetPos = pos.relative(direction);
			EnergyStorage targetStorage = EnergyStorage.SIDED.find(world, targetPos, direction.getOpposite());

			if (targetStorage != null && targetStorage.supportsInsertion()) {

				long maxToMove;
				if (world.getBlockEntity(targetPos) instanceof RedCableEntity neighborCable) {
					long currentEnergy = blockEntity.energyStorage.getAmount();
					long neighborEnergy = neighborCable.energyStorage.getAmount();

					if (currentEnergy <= neighborEnergy) {
						continue;
					}

					long difference = currentEnergy - neighborEnergy;
					maxToMove = difference / 2;
				} else {
					maxToMove = Math.min(blockEntity.energyStorage.getAmount(), blockEntity.energyStorage.maxExtract);
				}

				if (maxToMove <= 0) continue;

				try (Transaction transaction = Transaction.openOuter()) {
					long extracted = blockEntity.energyStorage.extract(maxToMove, transaction);

					if (extracted > 0) {
						long inserted = targetStorage.insert(extracted, transaction);

						if (inserted > 0) {
							transaction.commit();
						}
					}
				}
			}
		}
	}
}