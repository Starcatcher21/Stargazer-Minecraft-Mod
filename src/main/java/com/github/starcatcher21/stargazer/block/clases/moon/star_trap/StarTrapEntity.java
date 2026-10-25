package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
//? if >= 26.2 {
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.state.AnimationTest;
//?} else {
/*import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import com.geckolib.animatable.GeoEntity;
*///?}
import com.geckolib.animation.*;
import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.animatable.GeoBlockEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.util.GeckoLibUtil;

public class StarTrapEntity extends BlockEntity implements GeoBlockEntity {
    private boolean active;

    protected static final RawAnimation ACTIVE =
            RawAnimation.begin().thenPlayAndHold("animation.star_trap.activated");
    protected static final RawAnimation NOTACTIVE =
            RawAnimation.begin().thenPlay("animation.star_trap.disactivate");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StarTrapEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_TRAP.get(), pos, state);
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
        controllers.add(new AnimationController<GeoAnimatable>(/*? if < 26.2 {*//*this, "controller", 5, *//*? } */this::animControler));
    }

    //? if >= 26.2 {
    protected <E extends StarTrap> PlayState animControler(final AnimationTest<?> animTest) {
    //? } else {
    /*protected PlayState animControler(final AnimationState<GeoAnimatable> animTest) {
    *///? }
        if (getBlockState().getValue(StarTrap.ACTIVE)) {
            animTest.setAnimation(ACTIVE);
        } else {
            animTest.setAnimation(NOTACTIVE);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /* ---------------- serialization ---------------------------------- */
    //? if >= 26.2 {
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
    //?} else {
    /*@Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        // CompoundTag.getBoolean returns false when the key is absent,
        // matching getBooleanOr("active", false).
        active = nbt.getBoolean("active");

        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 0);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        nbt.putBoolean("active", active);
    }
    *///?}
}
