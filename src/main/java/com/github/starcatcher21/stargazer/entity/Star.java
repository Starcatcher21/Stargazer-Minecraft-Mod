package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.Helpers;
import com.github.starcatcher21.stargazer.RegistryKeys;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.WishingStars;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityPositionSyncS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.rule.GameRules;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Star extends AbstractBoatEntity implements GeoEntity {
    protected static final RawAnimation ROTATO = RawAnimation.begin().thenLoop("star.rotate");

    private static final TrackedData<String> DYE_COLOR_NAME = DataTracker.registerData(Star.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<StarPatternsComponent> PATTEN = DataTracker.registerData(Star.class, RegistryKeys.PATTERN_COMPONENT);

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private final PositionInterpolator interpolator = new PositionInterpolator((Entity)this, 3);

    public Star(EntityType<? extends Star> type, World world) {
        super(type, world, () -> WishingStars.WISHING_STAR);
        this.setNoGravity(true);
    }

    @Override
    public void killAndDropItem(ServerWorld world, Item item) {
        this.kill(world);
        if (world.getGameRules().getValue(GameRules.ENTITY_DROPS)) {
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
            itemStack.set(DataComponentTypes.CUSTOM_NAME, this.getCustomName());
            itemStack.set(ComponentTypes.STAR_PATTERNS, getSPC());
            this.dropStack(world, itemStack);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DYE_COLOR_NAME, DyeColor.YELLOW.getId());
        var pat = new StarPatternsComponent(List.of());
        builder.add(PATTEN, pat);
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
    public void initPosition(double x, double y, double z) {
        super.initPosition(x, y, z);
    }

    @Override
    protected double getPassengerAttachmentY(EntityDimensions dimensions) {
        return 0.3;
    }

    @Override
    public void onPassengerLookAround(Entity passenger) {
        this.setYaw(passenger.getYaw());
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (passenger instanceof PlayerEntity pe) {
            pe.getAbilities().allowFlying = true;
            pe.getAbilities().flying = true;
        }
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (passenger instanceof PlayerEntity pe) {
            if (pe.isInCreativeMode()) return;
            pe.getAbilities().allowFlying = false;
            pe.getAbilities().flying = false;
        }
    }

    @Override
    protected int getMaxPassengers() {
        return 1;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        Vec3d vec3d = this.getPassengerRidingPos(passenger);
        Vec3d vec3d2 = passenger.getVehicleAttachmentPos(this);
        positionUpdater.accept(passenger, vec3d.x - vec3d2.x, vec3d.y - vec3d2.y, vec3d.z - vec3d2.z);
    }

    private int pt = 0;
    @Override
    public void tick() {
        super.tick();
        this.interpolator.tick();
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.getControllingPassenger() != null) {
                this.updateVelocity(this.getControllingPassenger().getMovementSpeed(), this.getControllingPassenger().getMovement());
                this.move(MovementType.SELF, this.getVelocity());
            }
        } else {
            this.setVelocity(Vec3d.ZERO);
        }
        pt += 1;
        if (pt >= 5) {
            TintedParticleEffect tintedParticleEffect = TintedParticleEffect.create(Particles.TINTED_STAR, getDyeColor() != null ? getDyeColor().getFireworkColor() : 0xffff00);
            Helpers.spawnParticle(this.getEntityWorld(), this.getEntityPos(), random, tintedParticleEffect);
            pt = 0;
        }
        if (!this.getEntityWorld().isClient()) {
            if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
                this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
                serverWorld.getChunkManager().chunkLoadingManager.sendToOtherNearbyPlayers(this, EntityPositionSyncS2CPacket.create(this));
            }
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void updateVelocity(float speed, Vec3d movementInput) {
        Vec3d vec3d = Entity.movementInputToVelocity(movementInput, speed*2, this.getYaw());
        this.addVelocity(vec3d);
        this.addVelocity(0.0, this.getFinalGravity(), 0.0);
    }

    public DyeColor getDyeColor() {
        return DyeColor.byId(this.dataTracker.get(DYE_COLOR_NAME), DyeColor.YELLOW);
    }

    public void setDyeColor(DyeColor t) {
        if (t != null) {
            this.dataTracker.set(DYE_COLOR_NAME, t.getId());
        }
    }

    public StarPatternsComponent getSPC() {
        return this.dataTracker.get(PATTEN);
    }

    public void setSPC(Optional<StarPatternsComponent> t) {
        t.ifPresent(starPatternsComponent ->
                {
                    this.dataTracker.set(PATTEN, starPatternsComponent);
                });
    }
    public void setSPC(PlayerEntity player, Identifier name) {
        DynamicRegistryManager drm = player.getRegistryManager();
        try {
            Patterns sp = drm.getOptional(RegistryKeys.STAR_PATTERN).get().get(name);
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
    public void writeCustomData(WriteView nbt) {
        super.writeCustomData(nbt);
        nbt.putString("dyeColor", getDyeColor().asString()); // standard vanilla naming format
        nbt.put("star_patterns", StarPatternsComponent.CODEC, getSPC());
    }

    @Override
    public void readCustomData(ReadView nbt) {
        super.readCustomData(nbt);
        String colorName = nbt.getString("dyeColor", "yellow");
        DyeColor color = DyeColor.byId(colorName, DyeColor.YELLOW);
        setDyeColor(color);
        if (nbt.contains("star_patterns")) {
            try {
                setSPC(nbt.read("star_patterns", StarPatternsComponent.CODEC));
            } catch (Exception ignored) {}
        }
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ActionResult actionResult = super.interact(player, hand);
        if (actionResult != ActionResult.PASS) {
            return actionResult;
        }

        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();

        if (player instanceof ServerPlayerEntity spe) {
            Criterias.starModifier.trigger(spe, item);
        }

        if (item instanceof DyeItem dyeItem) {
            setDyeColor(dyeItem.getColor());
            spawnParticles();
            return ActionResult.SUCCESS;
        }

        for (Map.Entry<StarPattern, List<Item>> entry : Patterns.itemList.entrySet()) {
            ActionResult result = isItem(item, entry.getValue(), player, entry.getKey());
            if (result.equals(ActionResult.SUCCESS)) return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
    public ActionResult isItem(Item item, List<Item> items, PlayerEntity player, StarPattern pattern) {
        if (items.contains(item)) {
            setSPC(player, pattern.assetId());
            spawnParticles();
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    public void spawnParticles() {
        for (int i = 0; i <= 20; i++) {
            this.getEntityWorld().addParticleClient(ParticleTypes.HAPPY_VILLAGER, this.getEntityPos().x - 0.5 + this.random.nextFloat(), this.getEntityPos().y - 0.5 + this.random.nextFloat(), this.getEntityPos().z - 0.5 + this.random.nextFloat(), 0.0, 0.0, 0.0);
        }
    }
}