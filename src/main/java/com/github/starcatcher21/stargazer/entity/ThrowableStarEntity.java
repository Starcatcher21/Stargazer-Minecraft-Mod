package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ThrowableStarEntity extends ThrowableItemProjectile {
    public ThrowableStarEntity(EntityType<? extends ThrowableStarEntity> entityType, Level world) {
        super(entityType, world);
    }

    public ThrowableStarEntity(Level world, LivingEntity owner, ItemStack stack) {
        super(EntityRegistry.THROWABLE_STAR_ENTITY, owner, world, stack);
    }

    public ThrowableStarEntity(Level world, double x, double y, double z, ItemStack stack) {
        super(EntityRegistry.THROWABLE_STAR_ENTITY, x, y, z, world, stack);
    }

    private ParticleOptions getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleOptions) Particles.STAR;
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.DEATH) {
            ParticleOptions particleEffect = this.getParticleParameters();

            this.level().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THROWABLE_STAR;
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof Blaze ? 3 : 6;
        entity.setRemainingFireTicks(200);
        entity.setSharedFlagOnFire(true);
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), i);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide()) {
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
        }
    }
}
