package com.github.starcatcher21.stargazer.mechanics.star;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.github.starcatcher21.stargazer.mechanics.star.Stargaze.range;

public class FallingObject {
    private final static Random random = new Random();
    public static final Codec<FallingObject> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Item.ENTRY_CODEC.fieldOf("item").forGetter(FallingObject::getItem),
            Registries.PARTICLE_TYPE.getCodec().fieldOf("fall_particle").forGetter(FallingObject::getFallParticle),
            Registries.PARTICLE_TYPE.getCodec().fieldOf("hit_particle").forGetter(FallingObject::getHitParticle),
            Codecs.POSITIVE_INT.fieldOf("particle_amount").forGetter(FallingObject::getAmount),
            Codecs.POSITIVE_FLOAT.fieldOf("velocity").forGetter(FallingObject::getVelocity)
    ).apply(instance, FallingObject::new));

    private ParticleType<?> getHitParticle() {
        return this.getHitParticle();
    }

    private ParticleType<?> getFallParticle() {
        return this.getFallParticle();
    }

    private Float getVelocity() {
        return this.velocity;
    }

    private Integer getAmount() {
        return this.amount;
    }

    private RegistryEntry<Item> getItem() {
        return this.item;
    }

    public final RegistryEntry<Item> item;
    public final int amount;
    public final float velocity;
    public final ParticleType<?> fallParticle;
    public final ParticleType<?> hitParticle;

    public FallingObject(RegistryEntry<Item> itemConstruct, ParticleType<?> fallParticleType, ParticleType<?> hitParticleType, int particleAmount, float particleVelocity) {
        item = itemConstruct;
        amount = particleAmount;
        velocity = particleVelocity;
        fallParticle = fallParticleType;
        hitParticle = hitParticleType;
    }

    public void hitParticles(World world, int X, int Y, int Z) {
        if (world instanceof ServerWorld sw) {
            sw.spawnParticles((SimpleParticleType) hitParticle, X + 0.5D, Y + 0.5D, Z + 0.5D, 30, -velocity + random.nextDouble(velocity*2d), -velocity + random.nextDouble(velocity*2d), -velocity + random.nextDouble(velocity *2d), 0.06);
        }
        for (int i = 0; i < 30; i++) {
            world.addParticleClient((SimpleParticleType) hitParticle, true, true, X + 0.5F, Y + 0.5F, Z + 0.5F, -velocity + random.nextFloat(velocity*2), -velocity + random.nextFloat(velocity*2), -velocity + random.nextFloat(velocity *2));
        }
    }

    public void fallParticles(World world, int X, int Y, int Z) {
        if (world instanceof ServerWorld sw) {
            sw.spawnParticles((SimpleParticleType) fallParticle, X + 0.5F, Y + 200+ 0.5F, Z + 0.5F, amount, 0.0, -amount/4.0F, 0.0, 0.06);
        }
        for (int i = 1; i <= amount; i++) {
            world.addParticleClient((SimpleParticleType) fallParticle, true, true, X + 0.5F, Y + 200+ 0.5F, Z + 0.5F, 0.0, -amount/4.0F, 0.0);
        }
    }
    public void spawn(MinecraftClient client, int X, int Y, int Z) {
        fallParticles(client.world, X, Y, Z);
        hitParticles(client.world, X, Y, Z);
        client.getServer().getOverworld().spawnEntity(new ItemEntity(client.getServer().getOverworld(), X+0.5, Y+0.5, Z+0.5, new ItemStack(item)));
    }
    public int airEnd(World world, BlockPos pos) {
        if (world.isAir(pos)) {
            if (!(world.isAir(pos.down(1)))) {
                return pos.getY();
            } else {
                return airEnd(world, pos.down(1));
            }
        } else {
            return airEnd(world, pos.up(1));
        }
    }
    public void spawn(MinecraftClient client, PlayerEntity player) {
        int X = (int) player.getX() - range + random.nextInt(range * 2);
        int Y = (int) player.getY();
        int Z = (int) player.getZ() - range + random.nextInt(range * 2);
        Y = airEnd(client.world, new BlockPos(X, Y, Z));
        spawn(client, X, Y, Z);
    }
}