package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.starlib.Helpers;
import com.github.starcatcher21.stargazer.RegistryKeys;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.WishingStars;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Star extends AbstractBoat implements GeoEntity {
    protected static final RawAnimation ROTATO = RawAnimation.begin().thenLoop("star.rotate");

    private static final EntityDataAccessor<String> DYE_COLOR_NAME = SynchedEntityData.defineId(Star.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<StarPatternsComponent> PATTEN = SynchedEntityData.defineId(Star.class, RegistryKeys.PATTERN_COMPONENT);

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private final InterpolationHandler interpolator = new InterpolationHandler((Entity)this, 3);

    public Star(EntityType<? extends Star> type, Level world) {
        super(type, world, () -> WishingStars.WISHING_STAR);
        this.setNoGravity(true);
    }

    @Override
    public void destroy(ServerLevel world, Item item) {
        this.kill(world);
        if (world.getGameRules().get(GameRules.ENTITY_DROPS)) {
            Item item2;
            switch (getDyeColor()) {
                case LIGHT_GRAY -> item2 = WishingStars.LIGHT_GRAY_WISHING_STAR;
                case LIGHT_BLUE -> item2 = WishingStars.LIGHT_BLUE_WISHING_STAR;
                case MAGENTA -> item2 = WishingStars.MAGENTA_WISHING_STAR;
                case PURPLE -> item2 = WishingStars.PURPLE_WISHING_STAR;
                case ORANGE -> item2 = WishingStars.ORANGE_WISHING_STAR;
                case GREEN -> item2 = WishingStars.GREEN_WISHING_STAR;
                case BROWN -> item2 = WishingStars.BROWN_WISHING_STAR;
                case LIME -> item2 = WishingStars.LIME_WISHING_STAR;
                case GRAY -> item2 = WishingStars.GRAY_WISHING_STAR;
                case CYAN -> item2 = WishingStars.CYAN_WISHING_STAR;
                case RED -> item2 = WishingStars.RED_WISHING_STAR;
                case BLUE -> item2 = WishingStars.BLUE_WISHING_STAR;
                case PINK -> item2 = WishingStars.PINK_WISHING_STAR;
                case BLACK -> item2 = WishingStars.BLACK_WISHING_STAR;
                case WHITE -> item2 = WishingStars.WHITE_WISHING_STAR;
                case null, default -> item2 = WishingStars.WISHING_STAR;
            }
            ItemStack itemStack = new ItemStack(item2);
            itemStack.set(DataComponents.CUSTOM_NAME, this.getCustomName());
            itemStack.set(ComponentTypes.STAR_PATTERNS, getSPC());
            this.spawnAtLocation(world, itemStack);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DYE_COLOR_NAME, DyeColor.YELLOW.getName());
        var pat = new StarPatternsComponent(List.of());
        builder.define(PATTEN, pat);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>("MovementController", 5, this::AnimController));
    }

    private PlayState AnimController(AnimationTest<GeoAnimatable> animTest) {
        return animTest.setAndContinue(ROTATO);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public void setInitialPos(double x, double y, double z) {
        super.setInitialPos(x, y, z);
    }

    @Override
    protected double rideHeight(EntityDimensions dimensions) {
        return 0.3;
    }

    @Override
    public void onPassengerTurned(Entity passenger) {
        this.setYRot(passenger.getYRot());
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (passenger instanceof Player pe) {
            pe.getAbilities().mayfly = true;
            pe.getAbilities().flying = true;
        }
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (passenger instanceof Player pe) {
            if (pe.hasInfiniteMaterials()) return;
            pe.getAbilities().mayfly = false;
            pe.getAbilities().flying = false;
        }
    }

    @Override
    protected int getMaxPassengers() {
        return 1;
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction positionUpdater) {
        Vec3 vec3d = this.getPassengerRidingPosition(passenger);
        Vec3 vec3d2 = passenger.getVehicleAttachmentPoint(this);
        positionUpdater.accept(passenger, vec3d.x - vec3d2.x, vec3d.y - vec3d2.y, vec3d.z - vec3d2.z);
    }

    private int pt = 0;
    @Override
    public void tick() {
        super.tick();
        this.interpolator.interpolate();
        if (this.isLocalInstanceAuthoritative()) {
            if (this.getControllingPassenger() != null) {
                this.moveRelative(this.getControllingPassenger().getSpeed(), this.getControllingPassenger().getKnownMovement());
                this.move(MoverType.SELF, this.getDeltaMovement());
            }
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }
        pt += 1;
        if (pt >= 5) {
            ColorParticleOption tintedParticleEffect = ColorParticleOption.create(Particles.TINTED_STAR, getDyeColor() != null ? getDyeColor().getFireworkColor() : 0xffff00);
            Helpers.spawnParticle(this.level(), this.position(), random, tintedParticleEffect);
            pt = 0;
        }
        if (!this.level().isClientSide()) {
            if (this.level() instanceof ServerLevel serverWorld) {
                this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
                serverWorld.getChunkSource().chunkMap.sendToTrackingPlayers(this, ClientboundEntityPositionSyncPacket.of(this));
            }
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void moveRelative(float speed, Vec3 movementInput) {
        Vec3 vec3d = Entity.getInputVector(movementInput, speed*2, this.getYRot());
        this.push(vec3d);
        this.push(0.0, this.getGravity(), 0.0);
    }

    public DyeColor getDyeColor() {
        return DyeColor.byName(this.entityData.get(DYE_COLOR_NAME), DyeColor.YELLOW);
    }

    public void setDyeColor(DyeColor t) {
        if (t != null) {
            this.entityData.set(DYE_COLOR_NAME, t.getName());
        }
    }

    public StarPatternsComponent getSPC() {
        return this.entityData.get(PATTEN);
    }

    public void setSPC(Optional<StarPatternsComponent> t) {
        t.ifPresent(starPatternsComponent ->
                {
                    this.entityData.set(PATTEN, starPatternsComponent);
                });
    }
    public void setSPC(Player player, Identifier name) {
        RegistryAccess drm = player.registryAccess();
        try {
            Patterns sp = drm.lookup(RegistryKeys.STAR_PATTERN).get().getValue(name);
            List<StarPattern> l = Patterns.patternList.stream().filter(starPattern -> starPattern.assetId().equals(sp.pattern)).toList();
            if (!l.isEmpty()) {
                setSPC(Optional.of(new StarPatternsComponent(List.of(new StarPatternsComponent.Layer(l.getFirst(), DyeColor.WHITE)))));
            }
        } catch (Exception e) {
            Stargazer.LOGGER.error("Failed to set StarPattern: " + e);
        }
    }

    // 4. Update NBT methods to seamlessly interface with the data tracker
    @Override
    public void addAdditionalSaveData(ValueOutput nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("dyeColor", getDyeColor().getSerializedName()); // standard vanilla naming format
        nbt.store("star_patterns", StarPatternsComponent.CODEC, getSPC());
    }

    @Override
    public void readAdditionalSaveData(ValueInput nbt) {
        super.readAdditionalSaveData(nbt);
        String colorName = nbt.getStringOr("dyeColor", "yellow");
        DyeColor color = DyeColor.byName(colorName, DyeColor.YELLOW);
        setDyeColor(color);
        if (nbt.contains("star_patterns")) {
            try {
                setSPC(nbt.read("star_patterns", StarPatternsComponent.CODEC));
            } catch (Exception ignored) {}
        }
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        InteractionResult actionResult = super.interact(player, hand, location);
        if (actionResult != InteractionResult.PASS) {
            return actionResult;
        }

        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        if (player instanceof ServerPlayer spe) {
            Criterias.starModifier.trigger(spe, item);
        }

        if (item instanceof DyeItem dyeItem) {
            setDyeColor(DyeColor.byId(DyeItem.getId(dyeItem)));
            spawnParticles();
            return InteractionResult.SUCCESS;
        }

        for (Map.Entry<StarPattern, List<Item>> entry : Patterns.itemList.entrySet()) {
            InteractionResult result = isItem(item, entry.getValue(), player, entry.getKey());
            if (result.equals(InteractionResult.SUCCESS)) return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
    public InteractionResult isItem(Item item, List<Item> items, Player player, StarPattern pattern) {
        if (items.contains(item)) {
            setSPC(player, pattern.assetId());
            spawnParticles();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
    public void spawnParticles() {
        for (int i = 0; i <= 20; i++) {
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.position().x - 0.5 + this.random.nextFloat(), this.position().y - 0.5 + this.random.nextFloat(), this.position().z - 0.5 + this.random.nextFloat(), 0.0, 0.0, 0.0);
        }
    }
}