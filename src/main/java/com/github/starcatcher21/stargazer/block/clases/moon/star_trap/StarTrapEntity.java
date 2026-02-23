package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StarTrapEntity extends BlockEntity implements GeoBlockEntity {
    private boolean active;

    protected static final RawAnimation ACTIVE = RawAnimation.begin().thenPlayAndHold("animation.star_trap.activated");
    protected static final RawAnimation NOTACTIVE = RawAnimation.begin().thenPlay("animation.star_trap.disactivate");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StarTrapEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_TRAP, pos, state);
        this.active = state.get(StarTrap.ACTIVE);
    }

    public void setActive(Boolean ac) {
        active = ac;
        markDirty();
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
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        active = nbt.getBoolean("active").orElse(false);

        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 0);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putBoolean("active", active);
    }
}
