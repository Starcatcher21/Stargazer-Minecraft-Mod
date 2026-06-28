package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ThrowableStarEntity extends ThrownItemEntity {
    public ThrowableStarEntity(EntityType<? extends ThrowableStarEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrowableStarEntity(World world, LivingEntity owner, ItemStack stack) {
        super(EntityRegistry.THROWABLE_STAR_ENTITY, owner, world, stack);
    }

    public ThrowableStarEntity(World world, double x, double y, double z, ItemStack stack) {
        super(EntityRegistry.THROWABLE_STAR_ENTITY, x, y, z, world, stack);
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect) Particles.STAR;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();

            this.getEntityWorld().addParticleClient(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THROWABLE_STAR;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof BlazeEntity ? 3 : 6;
        entity.setFireTicks(200);
        entity.setOnFire(true);
        entity.serverDamage(this.getDamageSources().thrown(this, this.getOwner()), i);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getEntityWorld().isClient()) {
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getEntityWorld().isClient()) {
            this.getEntityWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
        }
    }
}
