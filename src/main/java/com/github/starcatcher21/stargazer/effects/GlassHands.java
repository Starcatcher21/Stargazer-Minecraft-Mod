package com.github.starcatcher21.stargazer.effects;

import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class GlassHands extends MobEffect {
    public GlassHands(MobEffectCategory category, int color) {
        super(category, color);
    }

    protected GlassHands(MobEffectCategory category, int color, ParticleOptions particleEffect) {
        super(category, color, particleEffect);
    }

    @Override
    public void onMobHurt(ServerLevel world, LivingEntity entity, int amplifier, DamageSource source, float amount) {
        ItemStack mh = entity.getMainHandItem();
        ItemStack oh = entity.getOffhandItem();
        if (!mh.isEmpty()) {
            if (mh.isDamageableItem()) {
                mh.hurtAndConvertOnBreak((amplifier+1)*5, mh.getItem(),entity, entity.getEquipmentSlotForItem(mh));
            } else {
                mh.shrink(amplifier+1);
            }
        } else if (!oh.isEmpty()) {
            if (oh.isDamageableItem()) {
                oh.hurtAndConvertOnBreak((amplifier+1)*2, oh.getItem(),entity, entity.getEquipmentSlotForItem(oh));
            } else {
                oh.shrink(amplifier+1);
            }
        } else {
            DamageSource damageSource = new DamageSource(
                    world.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(DamageTypeRegistry.GLASS_CANNON.identifier()).get()
            );
            entity.hurtServer(world, damageSource, entity.getHealth()-1);
        }
    }
}
