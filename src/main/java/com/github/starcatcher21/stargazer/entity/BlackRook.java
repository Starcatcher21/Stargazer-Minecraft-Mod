package com.github.starcatcher21.stargazer.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BlackRook extends PathAwareEntity implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public BlackRook(EntityType<? extends BlackRook> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createCreatureAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.15);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>("MovementController", 5, this::AnimController));
    }

    private PlayState AnimController(AnimationTest<GeoAnimatable> animTest) {
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 0.8D, true));
        this.goalSelector.add(1, new AttackGoal(this));
        this.goalSelector.add(1, new ActiveTargetGoal<Rook>(this, Rook.class, true));
        this.goalSelector.add(2, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
        this.goalSelector.add(2, new WanderAroundGoal(this, 0.8D));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }
}