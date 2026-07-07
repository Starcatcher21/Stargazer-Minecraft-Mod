package com.github.starcatcher21.stargazer.block.clases.energy.cables;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class BlueCableEntity extends BlockEntity implements EnergyStorage {
	public BlueCableEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.BLUE_CABLE, pos, state);
	}

	public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(5000, 5000, 5000) {
		@Override
		protected void onFinalCommit() {
			markDirty();
		}
	};

	@Override
	protected void readData(ReadView nbt) {
		super.readData(nbt);
		energyStorage.amount = nbt.getLong("energy", 0);
	}

	@Override
	protected void writeData(WriteView nbt) {
		super.writeData(nbt);
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

	public static void tick(World world, BlockPos pos, BlockState state, BlueCableEntity blockEntity) {
		if (world.isClient() || blockEntity.energyStorage.getAmount() <= 0) return;

		for (Direction direction : Direction.values()) {
			if (blockEntity.energyStorage.getAmount() <= 0) break;

			BlockPos targetPos = pos.offset(direction);
			EnergyStorage targetStorage = EnergyStorage.SIDED.find(world, targetPos, direction.getOpposite());

			if (targetStorage != null && targetStorage.supportsInsertion()) {

				long maxToMove;
				if (world.getBlockEntity(targetPos) instanceof BlueCableEntity neighborCable) {
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