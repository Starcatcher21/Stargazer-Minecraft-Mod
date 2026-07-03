package com.github.starcatcher21.stargazer.block.fluids;

import com.github.starcatcher21.stargazer.block.ModFluids;
import com.github.starcatcher21.stargazer.block.register.Fluids;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.CollisionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Optional;

public class DreamFlowable extends FlowableFluid {
	@Override
	public Fluid getFlowing() {
		return ModFluids.DREAM_FLOWING;
	}

	@Override
	public Fluid getStill() {
		return ModFluids.DREAM;
	}

	@Override
	public Item getBucketItem() {
		return ModItems.DREAM_BUCKET;
	}

	@Override
	protected void randomDisplayTick(World world, BlockPos pos, FluidState state, Random random) {
		if (!state.isStill() && !(Boolean)state.get(FALLING)) {
			if (random.nextInt(64) == 0) {
				world.playSoundClient(
						pos.getX() + 0.5,
						pos.getY() + 0.5,
						pos.getZ() + 0.5,
						SoundEvents.BLOCK_WATER_AMBIENT,
						SoundCategory.AMBIENT,
						random.nextFloat() * 0.25F + 0.75F,
						random.nextFloat() + 0.5F,
						false
				);
			}
		} else if (random.nextInt(10) == 0) {
			world.addParticleClient(
					Particles.STAR, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0
			);
		}
	}

	@Override
	protected void onEntityCollision(World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {

		Vec3d currentVelocity = entity.getVelocity();

		entity.setVelocity(
				currentVelocity.x * 0.2,
				(currentVelocity.y * -0.8),
				currentVelocity.z * 0.2
		);

		if (entity instanceof LivingEntity living) {
			if (living.isJumping()) {
				entity.addVelocity(0, 0.08, 0);
			}
			if (living.isSneaking()) {
				entity.addVelocity(0, -0.08, 0);
			}
		}
		handler.addEvent(CollisionEvent.EXTINGUISH);
		if (entity.canFreeze()) {
			handler.addEvent(CollisionEvent.FREEZE);
		}
	}

	@Override
	protected boolean isInfinite(ServerWorld world) {
		return false;
	}

	@Override
	protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
		BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
	}

	@Override
	public int getMaxFlowDistance(WorldView world) {
		return 2;
	}

	@Override
	public BlockState toBlockState(FluidState state) {
		return Fluids.DREAM.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
	}

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
	public boolean matchesType(Fluid fluid) {
		return fluid == ModFluids.DREAM || fluid == ModFluids.DREAM_FLOWING;
	}

	@Override
	public int getLevelDecreasePerBlock(WorldView world) {
		return 1;
	}

    @Override
    public int getLevel(FluidState state) {
        return state.getLevel();
    }

    @Override
	public int getTickRate(WorldView world) {
		return 5;
	}

	@Override
	public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
		return direction == Direction.DOWN;
	}

	@Override
	protected float getBlastResistance() {
		return 100.0F;
	}

	@Override
	public Optional<SoundEvent> getBucketFillSound() {
		return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
	}

    public static class Flowing extends DreamFlowable {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return (Integer)state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends DreamFlowable {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
