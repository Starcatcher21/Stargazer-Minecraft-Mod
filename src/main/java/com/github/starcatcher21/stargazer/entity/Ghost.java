package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.models.GhostModel;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Ghost extends PathAwareEntity implements GeoEntity {
    protected static final RawAnimation FLY_ANIM = RawAnimation.begin().thenLoop("animation.ghost_move");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenPlay("animation.ghost_idle");
    protected static final RawAnimation IDLE2_ANIM = RawAnimation.begin().thenLoop("animation.ghost_idle2");

    private String TAG;
    public String CustomName = "";

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public Ghost(EntityType<? extends Ghost> type, World world) {
        super(type, world);
        this.setNoGravity(true);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.noClip = true;
        this.TAG = "";
    }

    public static DefaultAttributeContainer.Builder createFlyingCreatureAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 2.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.15)
                .add(EntityAttributes.FLYING_SPEED, 0.3);
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setMaxFollowRange(48.0F);
        return birdNavigation;
    }

    @Override
    public void playSound(@Nullable SoundEvent sound) {
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
    public void onDeath(DamageSource damageSource) {
        if (this.isRemoved() || this.dead) {
            return;
        }
        LivingEntity livingEntity = this.getPrimeAdversary();
        if (livingEntity != null) {
            livingEntity.updateKilledAdvancementCriterion(this, damageSource);
        }
        if (this.isSleeping()) {
            this.wakeUp();
        }
        if (!this.getEntityWorld().isClient() && this.hasCustomName()) {
            Stargazer.LOGGER.info("Named entity {} died: {}", (Object)this, (Object)this.getDamageTracker().getDeathMessage().getString());
        }
        this.dead = true;
        this.getDamageTracker().update();
        World world = this.getEntityWorld();
        this.emitGameEvent(GameEvent.ENTITY_DIE);
        if (world instanceof ServerWorld serverWorld) {
            this.drop(serverWorld, damageSource);
        }
        if (GhostModel.pacman.contains(this.getDisplayName().getString())) {
            return;
        }
        this.getEntityWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
        this.setPose(EntityPose.DYING);
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        if (damageSource.getAttacker() != null && damageSource.getAttacker() instanceof PlayerEntity pe) {
            pe.setVelocity(0,0,0);
            pe.setNoGravity(!pe.hasNoGravity());
            this.oldDamage(damageSource);
        } else {
            this.oldDamage(damageSource);
        }
    }
    private void oldDamage(DamageSource damageSource) {
        this.limbAnimator.setSpeed(0.5f);
        this.timeUntilRegen = 0;
        this.hurtTime = this.maxHurtTime = 0;
    }

    @Override
    public void travel(Vec3d movementInput) {
        super.travel(movementInput);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.hasPositionTarget() && this.random.nextInt(32) > 24) {
            this.setTargetPos(this.getEntityPos().add(random.nextFloat() * 10 - 5, random.nextFloat() * 10 - 5, random.nextFloat() * 10 - 5));
        }
        if (this.hasCustomName()) {
            CustomName = this.getStringifiedName().toLowerCase();
            if (GhostModel.pacman.contains(this.getStringifiedName().toLowerCase())) {
                if (!getTag().equals("pacman")) setTag("pacman");
            } else if (GhostModel.bill.contains(this.getStringifiedName().toLowerCase())) {
                if (!getTag().equals("bill")) setTag("bill");
            } else if (GhostModel.adventure.contains(this.getStringifiedName().toLowerCase())){
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
    public void writeCustomData(WriteView nbt) {
        super.writeCustomData(nbt);
        nbt.putString("tag", TAG);
        nbt.putString("name", CustomName);
    }

    @Override
    public void readCustomData(ReadView nbt) {
        super.readCustomData(nbt);
        TAG = nbt.getString("tag", "");
        CustomName = nbt.getString("name", "");
    }

    public void setTargetPos(Vec3d pos) {
        this.navigation.startMovingTo(pos.x, pos.y, pos.z, 0.7);
    }

    public boolean hasPositionTarget() {
        return !this.navigation.isIdle();
    }
}