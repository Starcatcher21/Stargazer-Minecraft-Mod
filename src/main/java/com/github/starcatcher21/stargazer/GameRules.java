package com.github.starcatcher21.stargazer;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;

//? if fabric {
/*import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
 *///? }

//? if >= 26.2 {
/*import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
*///? } else {
 //? }

public class GameRules {

    //? if >= 26.2 {
    /*public static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(Stargazer.MOD_ID, Registries.GAME_RULE);
    public static final RegistrySupplier<GameRule<Boolean>> DASH = register("allowdashing", true, GameRuleCategory.PLAYER);
    public static final RegistrySupplier<GameRule<Boolean>> MOON = register("showmoonphaseinrei", true, GameRuleCategory.MISC);
    *///? } else {
    public static final net.minecraft.world.level.GameRules.Key<net.minecraft.world.level.GameRules.BooleanValue> DASH = register("allowdashing", true, net.minecraft.world.level.GameRules.Category.PLAYER);
    public static final net.minecraft.world.level.GameRules.Key<net.minecraft.world.level.GameRules.BooleanValue> MOON = register("showmoonphaseinrei", true, net.minecraft.world.level.GameRules.Category.MISC);
    //? }

    //? if >= 26.2 {
    
    /*public static RegistrySupplier<GameRule<Boolean>> register(String name, boolean defaultValue, net.minecraft.world.level.gamerules.GameRuleCategory category) {
        //? if fabric {
        /^var gamerule = GameRuleBuilder.forBoolean(defaultValue)
                .category(category)
                .buildAndRegister(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, name));
        ^///? } else {
        var gamerule = new net.minecraft.world.level.gamerules.GameRule<>(
                category,
                net.minecraft.world.level.gamerules.GameRuleType.BOOL,
                BoolArgumentType.bool(),
                net.minecraft.world.level.gamerules.GameRuleTypeVisitor::visitBoolean,
                Codec.BOOL,
                b -> b ? 1 : 0,
                defaultValue,
                FeatureFlagSet.of()
        );
        //? }
        return GAME_RULES.register(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, name), () -> gamerule);
    }
    *///? } else {
    public static net.minecraft.world.level.GameRules.Key<net.minecraft.world.level.GameRules.BooleanValue> register(String name, boolean defaultValue, net.minecraft.world.level.GameRules.Category category) {
        net.minecraft.world.level.GameRules.Type<net.minecraft.world.level.GameRules.BooleanValue> type =
                net.minecraft.world.level.GameRules.BooleanValue.create(defaultValue);
        return net.minecraft.world.level.GameRules.register(name, category, type);
    }
    //? }

    public static void init() {
        //? if >= 26.2 && neoforge {
        /*GAME_RULES.register();
        *///? }
    }
}
