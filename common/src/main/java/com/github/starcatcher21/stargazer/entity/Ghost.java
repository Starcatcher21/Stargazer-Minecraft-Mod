package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.models.GhostModel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.util.GeckoLibUtil;

public class Ghost extends PathfinderMob implements GeoEntity {
    protected static final RawAnimation FLY_ANIM = RawAnimation.begin().thenLoop("animation.ghost_move");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenPlay("animation.ghost_idle");
    protected static final RawAnimation IDLE2_ANIM = RawAnimation.begin().thenLoop("animation.ghost_idle2");

    private String TAG;
    public String CustomName = "";

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public Ghost(EntityType<? extends Ghost> type, Level world) {
        super(type, world);
        this.setNoGravity(true);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.noPhysics = true;
        this.TAG = "";
    }

    public static AttributeSupplier.Builder createFlyingCreatureAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.FLYING_SPEED, 0.3);
    }

    protected PathNavigation createNavigation(Level world) {
        FlyingPathNavigation birdNavigation = new FlyingPathNavigation(this, world);
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanFloat(true);
        birdNavigation.setRequiredPathLength(48.0F);
        return birdNavigation;
    }

    @Override
    public void makeSound(@Nullable SoundEvent sound) {
        //super.playSound(sound);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>("MovementController", 5, this::AnimController));
    }

    private PlayState AnimController(AnimationTest<GeoAnimatable> animTest) {
        int rand = this.random.nextInt(5);
        if (rand > 3 & !animTest.isMoving()) {
            return animTest.setAndContinue(IDLE_ANIM);
        } else if (rand <= 3 & !animTest.isMoving()) {
            return animTest.setAndContinue(IDLE2_ANIM);
        } else if (animTest.isMoving()) {
            return animTest.setAndContinue(FLY_ANIM);
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public void die(DamageSource damageSource) {
        if (this.isRemoved() || this.dead) {
            return;
        }
        LivingEntity livingEntity = this.getKillCredit();
        if (livingEntity != null) {
            livingEntity.awardKillScore(this, damageSource);
        }
        if (this.isSleeping()) {
            this.stopSleeping();
        }
        if (!this.level().isClientSide() && this.hasCustomName()) {
            Stargazer.LOGGER.info("Named entity {} died: {}", (Object)this, (Object)this.getCombatTracker().getDeathMessage().getString());
        }
        this.dead = true;
        this.getCombatTracker().recheckStatus();
        Level world = this.level();
        this.gameEvent(GameEvent.ENTITY_DIE);
        if (world instanceof ServerLevel serverWorld) {
            this.dropAllDeathLoot(serverWorld, damageSource);
        }
        if (GhostModel.pacman.contains(this.getDisplayName().getString())) {
            return;
        }
        this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
        this.setPose(Pose.DYING);
    }

    @Override
    public void handleDamageEvent(DamageSource damageSource) {
        if (damageSource.getEntity() != null && damageSource.getEntity() instanceof Player pe) {
            pe.setDeltaMovement(0,0,0);
            pe.setNoGravity(!pe.isNoGravity());
            this.oldDamage(damageSource);
        } else {
            this.oldDamage(damageSource);
        }
    }
    private void oldDamage(DamageSource damageSource) {
        this.walkAnimation.setSpeed(0.5f);
        this.invulnerableTime = 0;
        this.hurtTime = this.hurtDuration = 0;
    }

    @Override
    public void travel(Vec3 movementInput) {
        super.travel(movementInput);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.hasHome() && this.random.nextInt(32) > 24) {
            this.setTargetPos(this.position().add(random.nextFloat() * 10 - 5, random.nextFloat() * 10 - 5, random.nextFloat() * 10 - 5));
        }
        if (this.hasCustomName()) {
            CustomName = this.getPlainTextName().toLowerCase();
            if (GhostModel.pacman.contains(this.getPlainTextName().toLowerCase())) {
                if (!getTag().equals("pacman")) setTag("pacman");
            } else if (GhostModel.bill.contains(this.getPlainTextName().toLowerCase())) {
                if (!getTag().equals("bill")) setTag("bill");
            } else if (GhostModel.adventure.contains(this.getPlainTextName().toLowerCase())){
                if (!getTag().equals("adventure")) setTag("adventure");
            } else {
                if (!getTag().isEmpty()) setTag("");
            }
        }
    }

    public String getTag() {
        return TAG;
    }

    public void setTag(String t) {
        TAG = t;
    }

    @Override
    public void addAdditionalSaveData(ValueOutput nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("tag", TAG);
        nbt.putString("name", CustomName);
    }

    @Override
    public void readAdditionalSaveData(ValueInput nbt) {
        super.readAdditionalSaveData(nbt);
        TAG = nbt.getStringOr("tag", "");
        CustomName = nbt.getStringOr("name", "");
    }

    public void setTargetPos(Vec3 pos) {
        this.navigation.moveTo(pos.x, pos.y, pos.z, 0.7);
    }

    public boolean hasHome() {
        return !this.navigation.isDone();
    }
}