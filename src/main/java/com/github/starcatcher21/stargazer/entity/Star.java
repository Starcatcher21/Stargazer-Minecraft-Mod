package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.CreakingEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.predicate.entity.EntityPredicates;
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

import java.util.List;

public class Star extends AbstractBoatEntity implements GeoEntity {
    protected static final RawAnimation ROTATO = RawAnimation.begin().thenLoop("star.rotate");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private final PositionInterpolator interpolator = new PositionInterpolator((Entity)this, 3);

    public Star(EntityType<? extends Star> type, World world) {
        super(type, world, () -> ModItems.YELLOW_STAR);
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
    protected double getPassengerAttachmentY(EntityDimensions dimensions) {
        return 0.3;
    }

    @Override
    public void onPassengerLookAround(Entity passenger) {
//        this.setYaw(passenger.getYaw());
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

    @Override
    public void tick() {
        super.tick();
        this.interpolator.tick();
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.getControllingPassenger() != null) {
                this.updateVelocity(1.0f, this.getControllingPassenger().getMovement());
                this.move(MovementType.SELF, this.getVelocity());
            }
        } else {
            this.setVelocity(Vec3d.ZERO);
        }
        this.tickBlockCollision();
        this.tickBlockCollision();
        List<Entity> list = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(0.2f, -0.01f, 0.2f), EntityPredicates.canBePushedBy(this));
        if (!list.isEmpty()) {
            boolean bl = !this.getWorld().isClient && !(this.getControllingPassenger() instanceof PlayerEntity);
            for (Entity entity : list) {
                if (entity.hasPassenger(this)) continue;
                if (bl && this.getPassengerList().size() < this.getMaxPassengers() && !entity.hasVehicle() && this.isSmallerThanBoat(entity) && entity instanceof LivingEntity && !(entity instanceof WaterCreatureEntity) && !(entity instanceof PlayerEntity) && !(entity instanceof CreakingEntity)) {
                    entity.startRiding(this);
                    continue;
                }
                this.pushAwayFrom(entity);
            }
        }
    }

    @Override
    public void updateVelocity(float speed, Vec3d movementInput) {
        Vec3d vec3d = Entity.movementInputToVelocity(movementInput, speed, this.getControllingPassenger() != null ? this.getControllingPassenger().getYaw() : this.getYaw());
        this.setVelocity(this.getVelocity().add(vec3d));
    }

}