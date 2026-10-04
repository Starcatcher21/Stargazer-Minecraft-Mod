package com.github.starcatcher21.stargazer;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
//? if fabric {
/*import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.ResourceLocation;
*///? }
//? if neoforge {
import net.minecraft.world.flag.FeatureFlagSet;
//? }

public class GameRules {

    //? if >= 26.2 {
    
    /*public static final net.minecraft.world.level.gamerules.GameRule<Boolean> DASH = register("allowdashing", true, net.minecraft.world.level.gamerules.GameRuleCategory.PLAYER);
    public static final net.minecraft.world.level.gamerules.GameRule<Boolean> MOON = register("showmoonphaseinrei", true, net.minecraft.world.level.gamerules.GameRuleCategory.MISC);
    
    *///? } else {
    public static final net.minecraft.world.level.GameRules.Key<?> DASH = register("allowdashing", true, net.minecraft.world.level.GameRules.Category.PLAYER);
    public static final net.minecraft.world.level.GameRules.Key<?> MOON = register("showmoonphaseinrei", true, net.minecraft.world.level.GameRules.Category.MISC);
    //? }

    //? if >= 26.2 {
    
    /*public static net.minecraft.world.level.gamerules.GameRule<Boolean> register(String name, boolean defaultValue, net.minecraft.world.level.gamerules.GameRuleCategory category) {
        //? if fabric {
        /^return GameRuleBuilder.forBoolean(defaultValue)
                .category(category)
                .buildAndRegister(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, name));
        ^///? } else {
        net.minecraft.world.level.gamerules.GameRule<Boolean> rule = new net.minecraft.world.level.gamerules.GameRule<>(
                category,
                net.minecraft.world.level.gamerules.GameRuleType.BOOL,
                BoolArgumentType.bool(),
                net.minecraft.world.level.gamerules.GameRuleTypeVisitor::visitBoolean,
                Codec.BOOL,
                b -> b ? 1 : 0,
                defaultValue,
                FeatureFlagSet.of()
        );
        return rule;
        //? }
    }
    
    *///? } else {
    public static net.minecraft.world.level.GameRules.Key<?> register(String name, boolean defaultValue, net.minecraft.world.level.GameRules.Category category) {
        //? if fabric {
        /*return GameRuleBuilder.forBoolean(defaultValue)
                .category(category)
                .buildAndRegister(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, name));
        *///? } else {
        net.minecraft.world.level.GameRules.Type<net.minecraft.world.level.GameRules.BooleanValue> type =
                net.minecraft.world.level.GameRules.BooleanValue.create(defaultValue);
        return net.minecraft.world.level.GameRules.register(name, category, type);
        //? }
    }
    //? }

    public static void init() {}
}
