package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Star extends AbstractBoatEntity implements GeoEntity {
    protected static final RawAnimation ROTATO = RawAnimation.begin().thenLoop("star.rotate");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private final PositionInterpolator interpolator = new PositionInterpolator((Entity)this, 3);

    public Star(EntityType<? extends Star> type, World world) {
        super(type, world, () -> ModItems.WISHING_STAR);
        this.setNoGravity(true);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("MovementController", 5, this::AnimController));
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
            EntityEffectParticleEffect entityEffectParticleEffect = EntityEffectParticleEffect.create(Particles.TINTED_STAR, 0xFFFF00);
            ParticleUtil.spawnParticle(this.getWorld(), this.getBlockPos(), random, entityEffectParticleEffect);
            pt = 0;
        }
    }

    @Override
    public void updateVelocity(float speed, Vec3d movementInput) {
        Vec3d vec3d;
        if (movementInput.y != 0) {
            vec3d = Entity.movementInputToVelocity(movementInput, speed*2, this.getYaw());
            this.addVelocity(vec3d);
            this.addVelocity(0.0, this.getFinalGravity(), 0.0);
        } else {
            vec3d = Entity.movementInputToVelocity(movementInput, speed, this.getYaw());
            this.addVelocity(vec3d);
            this.addVelocity(0.0, -1.0f, 0.0);
        }
    }
}