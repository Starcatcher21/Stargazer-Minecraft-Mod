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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
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
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
//? if >= 26.2 {
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.util.GeckoLibUtil;
//? } else {
/*import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animation.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.PlayState;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.world.level.GameRules;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.nbt.NbtOps;
*///? }

import java.util.List;
import java.util.Map;
import java.util.Optional;

//? if >= 26.2 {
public class Star extends AbstractBoat implements GeoEntity {
 //? } else {
/*public class Star extends Boat implements GeoEntity {
    *///? }
    protected static final RawAnimation ROTATO = RawAnimation.begin().thenLoop("star.rotate");

    private static final EntityDataAccessor<String> DYE_COLOR_NAME = SynchedEntityData.defineId(Star.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<StarPatternsComponent> PATTEN = SynchedEntityData.defineId(Star.class, RegistryKeys.PATTERN_COMPONENT2);

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    //? if >= 26.2 {
    private final InterpolationHandler interpolator = new InterpolationHandler((Entity)this, 3);
     //? }

    public Star(EntityType<? extends Star> type, Level world) {
        //? if >= 26.2 {
        super(type, world, () -> WishingStars.WISHING_STAR.get());
         //? } else {
        /*super(type, world);
        *///? }
        this.setNoGravity(true);
    }

    //? if >= 26.2 {
    @Override
     //? }
    public void destroy(ServerLevel world, Item item) {
        //? if >= 26.2 {
        this.kill(world);
         //? } else {
        /*this.kill();
        *///? }
        if (world.getGameRules()./*? if >= 26.2 {*/get( GameRules.ENTITY_DROPS/*? } else {*//*getBoolean(GameRules.RULE_DOENTITYDROPS*//*?}*/)) {
            Item item2;
            switch (getDyeColor()) {
                case LIGHT_GRAY -> item2 = WishingStars.LIGHT_GRAY_WISHING_STAR.get();
                case LIGHT_BLUE -> item2 = WishingStars.LIGHT_BLUE_WISHING_STAR.get();
                case MAGENTA -> item2 = WishingStars.MAGENTA_WISHING_STAR.get();
                case PURPLE -> item2 = WishingStars.PURPLE_WISHING_STAR.get();
                case ORANGE -> item2 = WishingStars.ORANGE_WISHING_STAR.get();
                case GREEN -> item2 = WishingStars.GREEN_WISHING_STAR.get();
                case BROWN -> item2 = WishingStars.BROWN_WISHING_STAR.get();
                case LIME -> item2 = WishingStars.LIME_WISHING_STAR.get();
                case GRAY -> item2 = WishingStars.GRAY_WISHING_STAR.get();
                case CYAN -> item2 = WishingStars.CYAN_WISHING_STAR.get();
                case RED -> item2 = WishingStars.RED_WISHING_STAR.get();
                case BLUE -> item2 = WishingStars.BLUE_WISHING_STAR.get();
                case PINK -> item2 = WishingStars.PINK_WISHING_STAR.get();
                case BLACK -> item2 = WishingStars.BLACK_WISHING_STAR.get();
                case WHITE -> item2 = WishingStars.WHITE_WISHING_STAR.get();
                case null, default -> item2 = WishingStars.WISHING_STAR.get();
            }
            ItemStack itemStack = new ItemStack(item2);
            itemStack.set(DataComponents.CUSTOM_NAME, this.getCustomName());
            itemStack.set(ComponentTypes.STAR_PATTERNS.get(), getSPC());
            //? if >= 26.2 {
            this.spawnAtLocation(world, itemStack);
             //? } else {
            /*this.spawnAtLocation(itemStack);
            *///? }
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
        //? if >= 26.2 {
        controllers.add(new AnimationController<GeoAnimatable>("MovementController", 5, this::AnimController));
         //? } else {
        /*controllers.add(new AnimationController<>(this, "MovementController", 5,
                state -> state.setAndContinue(ROTATO)));
        *///? }
    }

    //? if >= 26.2 {
    private PlayState AnimController(AnimationTest<GeoAnimatable> animTest) {
        return animTest.setAndContinue(ROTATO);
    }
    //? }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    //? if >= 26.2 {
    @Override
    public void setInitialPos(double x, double y, double z) {
        super.setInitialPos(x, y, z);
    }

    @Override
    protected double rideHeight(EntityDimensions dimensions) {
        return 0.3;
    }
    //? }

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
            //? if >= 1.21.2 {
            if (pe.hasInfiniteMaterials()) return;
             //? } else {
            /*if (pe.isCreative()) return;
            *///? }
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
        //? if >= 26.2 {
        this.interpolator.interpolate();
        if (this.isLocalInstanceAuthoritative()) {
            if (this.getControllingPassenger() != null) {
                this.moveRelative(this.getControllingPassenger().getSpeed(), this.getControllingPassenger().getKnownMovement());
                this.move(MoverType.SELF, this.getDeltaMovement());
            }
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }
        //? } else {
        /*if (this.getControllingPassenger() != null) {
            this.moveRelative(this.getControllingPassenger().getSpeed(), this.getControllingPassenger().getKnownMovement());
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
        *///? }
        pt += 1;
        if (pt >= 5) {
            ColorParticleOption tintedParticleEffect = ColorParticleOption.create(Particles.TINTED_STAR.get(), getDyeColor() != null ? getDyeColor().getFireworkColor() : 0xffff00);
            Helpers.spawnParticle(this.level(), this.position(), random, tintedParticleEffect);
            pt = 0;
        }
        if (!this.level().isClientSide()) {
            if (this.level() instanceof ServerLevel serverWorld) {
                this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
                //? if >= 26.2 {
                serverWorld.getChunkSource().chunkMap.sendToTrackingPlayers(this, ClientboundEntityPositionSyncPacket.of(this));
                 //? }
            }
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void moveRelative(float speed, Vec3 movementInput) {
        //? if >= 26.2 {
        Vec3 vec3d = Entity.getInputVector(movementInput, speed*2, this.getYRot());
         //? } else {
        /*Vec3 vec3d = getInputVector(movementInput, speed*2, this.getYRot());
        *///? }
        this.push(vec3d);
        this.push(0.0, this.getGravity(), 0.0);
    }

    //? if <= 1.21.1 {
    /*private static Vec3 getInputVector(Vec3 arg, float g, float h) {
        double d0 = arg.lengthSqr();
        if (d0 < 1.0E-7) {
            return Vec3.ZERO;
        } else {
            Vec3 vec3 = (d0 > (double)1.0F ? arg.normalize() : arg).scale((double)g);
            float f = Mth.sin(h * ((float)Math.PI / 180F));
            float f1 = Mth.cos(h * ((float)Math.PI / 180F));
            return new Vec3(vec3.x * (double)f1 - vec3.z * (double)f, vec3.y, vec3.z * (double)f1 + vec3.x * (double)f);
        }
    }
    *///? }

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
            //? if >= 26.2 {
            Patterns sp = drm.lookup(RegistryKeys.STAR_PATTERN).get().getValue(name);
             //? } else {
            /*Patterns sp = drm.registry(RegistryKeys.STAR_PATTERN).get().get(name);
            *///? }
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
    public void addAdditionalSaveData(/*? if >= 26.2 { */ValueOutput/*? } else { */ /*CompoundTag *//*? }*/ nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("dyeColor", getDyeColor().getSerializedName());
        //? if >= 26.2 {
        nbt.store("star_patterns", StarPatternsComponent.CODEC, getSPC());
         //? } else {
        /*StarPatternsComponent.CODEC
                .encodeStart(NbtOps.INSTANCE, getSPC())
                .resultOrPartial(err -> Stargazer.LOGGER.error("Failed to encode star patterns: {}", err))
                .ifPresent(tag -> nbt.put("star_patterns", tag));
        *///? }
    }

    @Override
    public void readAdditionalSaveData(/*? if >= 26.2 { */ValueInput/*? } else { */ /*CompoundTag *//*? } */ nbt) {
        super.readAdditionalSaveData(nbt);
        //? if >= 26.2 {
        String colorName = nbt.getStringOr("dyeColor", "yellow");
         //? } else {
        /*String colorName = nbt.getString("dyeColor");
        *///? }
        DyeColor color = DyeColor.byName(colorName, DyeColor.YELLOW);
        setDyeColor(color);
        //? if >= 26.2 {
        setSPC(nbt.read("star_patterns", StarPatternsComponent.CODEC));
         //? } else {
        /*setSPC(StarPatternsComponent.CODEC
                .parse(NbtOps.INSTANCE, nbt.get("star_patterns"))
                .resultOrPartial(err -> Stargazer.LOGGER.error("Failed to parse star patterns: {}", err)));
        *///? }
    }

    //? if >= 26.2 {
    @Override
    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        InteractionResult actionResult = super.interact(player, hand, location);
        if (actionResult != InteractionResult.PASS) {
            return actionResult;
        }
        return handleInteraction(player, hand);
    }
    //? } else {
    /*@Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        InteractionResult actionResult = super.interact(player, hand);
        if (actionResult != InteractionResult.PASS) {
            return actionResult;
        }
        return handleInteraction(player, hand);
    }
    *///? }

    private InteractionResult handleInteraction(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        if (player instanceof ServerPlayer spe) {
            Criterias.STAR_MODIFIER.listen(crit -> crit.trigger(spe, item));
        }

        if (item instanceof DyeItem dyeItem) {
            //? if >= 26.2 {
            setDyeColor(DyeColor.byId(DyeItem.getId(dyeItem)));
             //? } else {
            /*setDyeColor(dyeItem.getDyeColor());
            *///? }
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
