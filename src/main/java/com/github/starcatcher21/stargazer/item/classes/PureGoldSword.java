package com.github.starcatcher21.stargazer.item.classes;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PureGoldSword extends SwordItem {
    public PureGoldSword(Properties properties) {
        super(properties);
    }

    @Override
    public
    //? if < 26.1.2 {
    /*boolean
    *///? } else {
    void
    //? }
    hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        //? if < 26.1.2 {
        /*var hurt =
        *///? }
                super.hurtEnemy(itemStack, mob, attacker);
        mob.igniteForSeconds(5);
        //? if < 26.1.2 {
        /*return hurt;
        *///? }
    }
}
