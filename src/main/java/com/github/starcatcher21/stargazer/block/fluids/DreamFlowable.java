package com.github.starcatcher21.stargazer.block.fluids;

import com.github.starcatcher21.stargazer.block.ModFluids;
import com.github.starcatcher21.stargazer.block.register.Fluids;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.particle.Particles;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class DreamFlowable extends FlowingFluid {
	@Override
	public Fluid getFlowing() {
		return ModFluids.DREAM_FLOWING;
	}

	@Override
	public Fluid getSource() {
		return ModFluids.DREAM;
	}

	@Override
	public Item getBucket() {
		return ModItems.DREAM_BUCKET;
	}

	@Override
	protected void animateTick(Level world, BlockPos pos, FluidState state, RandomSource random) {
		if (!state.isSource() && !(Boolean)state.getValue(FALLING)) {
			if (random.nextInt(64) == 0) {
				world.playLocalSound(
						pos.getX() + 0.5,
						pos.getY() + 0.5,
						pos.getZ() + 0.5,
						SoundEvents.WATER_AMBIENT,
						SoundSource.AMBIENT,
						random.nextFloat() * 0.25F + 0.75F,
						random.nextFloat() + 0.5F,
						false
				);
			}
		} else if (random.nextInt(10) == 0) {
			world.addParticle(
					Particles.STAR, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0
			);
		}
	}

	@Override
	protected void entityInside(Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler) {

		Vec3 currentVelocity = entity.getDeltaMovement();

		entity.setDeltaMovement(
				currentVelocity.x * 0.2,
				(currentVelocity.y * -0.8),
				currentVelocity.z * 0.2
		);

		if (entity instanceof LivingEntity living) {
			if (living.isJumping()) {
				entity.push(0, 0.08, 0);
			}
			if (living.isShiftKeyDown()) {
				entity.push(0, -0.08, 0);
			}
		}
		handler.apply(InsideBlockEffectType.EXTINGUISH);
		if (entity.canFreeze()) {
			handler.apply(InsideBlockEffectType.FREEZE);
		}
	}

	@Override
	protected boolean canConvertToSource(ServerLevel world) {
		return false;
	}

	@Override
	protected void beforeDestroyingBlock(LevelAccessor world, BlockPos pos, BlockState state) {
		BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropResources(state, world, pos, blockEntity);
	}

	@Override
	public int getSlopeFindDistance(LevelReader world) {
		return 2;
	}

	@Override
	public BlockState createLegacyBlock(FluidState state) {
		return Fluids.DREAM.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
	}

    @Override
    public boolean isSource(FluidState state) {
        return false;
    }

    @Override
	public boolean isSame(Fluid fluid) {
		return fluid == ModFluids.DREAM || fluid == ModFluids.DREAM_FLOWING;
	}

	@Override
	public int getDropOff(LevelReader world) {
		return 1;
	}

    @Override
    public int getAmount(FluidState state) {
        return state.getAmount();
    }

    @Override
	public int getTickDelay(LevelReader world) {
		return 5;
	}

	@Override
	public boolean canBeReplacedWith(FluidState state, BlockGetter world, BlockPos pos, Fluid fluid, Direction direction) {
		return direction == Direction.DOWN;
	}

	@Override
	protected float getExplosionResistance() {
		return 100.0F;
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Optional.of(SoundEvents.BUCKET_FILL);
	}

    public static class Flowing extends DreamFlowable {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return (Integer)state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Still extends DreamFlowable {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
