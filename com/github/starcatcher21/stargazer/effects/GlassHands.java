package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;

public class GlassHands extends StatusEffect {
    public GlassHands(StatusEffectCategory category, int color) {
        super(category, color);
    }

    protected GlassHands(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
    }

    @Override
    public void onEntityDamage(ServerWorld world, LivingEntity entity, int amplifier, DamageSource source, float amount) {
        ItemStack mh = entity.getMainHandStack();
        ItemStack oh = entity.getOffHandStack();
        if (!mh.isEmpty()) {
            if (mh.isDamageable()) {
                mh.damage((amplifier+1)*5, mh.getItem(),entity, entity.getPreferredEquipmentSlot(mh));
            } else {
                mh.decrement(amplifier+1);
            }
        } else if (!oh.isEmpty()) {
            if (oh.isDamageable()) {
                oh.damage((amplifier+1)*2, oh.getItem(),entity, entity.getPreferredEquipmentSlot(oh));
            } else {
                oh.decrement(amplifier+1);
            }
        } else {
            DamageSource damageSource = new DamageSource(
                    world.getRegistryManager()
                            .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                            .getEntry(DamageTypeRegistry.GLASS_CANNON.getValue()).get()
            );
            entity.damage(world, damageSource, entity.getHealth()-1);
        }
    }
}
