package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StarDisplayEntity extends BlockEntity implements GeoBlockEntity {


    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
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

    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void setItems(DefaultedList<ItemStack> stack) {
        this.inventory = stack;
        markDirty();

        if (this.world != null) {
            // This forces the server to sync the packet to all nearby clients
            this.world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), 3);
        }
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return this.createNbt(registryLookup);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
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
