package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StarTrapEntity extends BlockEntity implements GeoBlockEntity {
    private boolean active;

    protected static final RawAnimation ACTIVE = RawAnimation.begin().thenPlayAndHold("animation.star_trap.activated");
    protected static final RawAnimation NOTACTIVE = RawAnimation.begin().thenPlay("animation.star_trap.disactivate");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StarTrapEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_TRAP, pos, state);
        this.active = state.getValue(StarTrap.ACTIVE);
    }

    public void setActive(Boolean ac) {
        active = ac;
        setChanged();
    }
    public boolean getActive() {
        return active;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this::animControler));
    }

    protected <E extends StarTrap> PlayState animControler(final AnimationTest<?> animTest) {
        if (this.getActive()) {
            animTest.setAnimation(ACTIVE);
        } else {
            animTest.setAnimation(NOTACTIVE);
        }
        return  PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void loadAdditional(ValueInput nbt) {
        super.loadAdditional(nbt);
        active = nbt.getBooleanOr("active", false);

        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 0);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput nbt) {
        super.saveAdditional(nbt);
        nbt.putBoolean("active", active);
    }
}
