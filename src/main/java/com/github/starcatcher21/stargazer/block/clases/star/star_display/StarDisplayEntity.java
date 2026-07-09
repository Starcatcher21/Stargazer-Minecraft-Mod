package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.animatable.GeoBlockEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.util.GeckoLibUtil;

public class StarDisplayEntity extends BlockEntity implements GeoBlockEntity {


    private NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    protected static final RawAnimation ROTATO = RawAnimation.begin().thenLoop("star.rotate");
    private float rotation = 360;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StarDisplayEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_DISPLAY, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this::animControler));
    }
    public float getRotation() {
        rotation -= 0.5f;
        if (rotation <= 0) {
            rotation = 360;
        }
        return rotation;
    }

    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public void setItems(NonNullList<ItemStack> stack) {
        this.inventory = stack;
        setChanged();

        if (this.level != null) {
            // This forces the server to sync the packet to all nearby clients
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return this.saveWithoutMetadata(registryLookup);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);
        ContainerHelper.loadAllItems(view, inventory);
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        ContainerHelper.saveAllItems(view, inventory);
    }

    public ItemStack getItem() {
        return inventory.getFirst();
    }

    protected <E extends StarDisplay> PlayState animControler(final AnimationTest<?> animTest) {
        return animTest.setAndContinue(ROTATO);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
