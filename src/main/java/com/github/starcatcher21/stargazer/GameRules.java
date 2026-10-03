package com.github.starcatcher21.stargazer;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
//? if fabric {
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
//? }
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

public class GameRules {
    //? if neoforge {
    /*public static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(Stargazer.MOD_ID, Registries.GAME_RULE);
    *///? }

    public static final GameRule<Boolean> DASH = register("allowdashing", true, GameRuleCategory.PLAYER);
    public static final GameRule<Boolean> MOON = register("showmoonphaseinrei", true, GameRuleCategory.MISC);

    public static GameRule<Boolean> register(String name, boolean defaultValue, GameRuleCategory category) {
        //? if fabric {
        return GameRuleBuilder.forBoolean(defaultValue)
                .category(category)
                .buildAndRegister(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name));

        //? } else {
        /*GameRule<Boolean> rule = new GameRule<>(
                category,
                GameRuleType.BOOL,
                BoolArgumentType.bool(),
                GameRuleTypeVisitor::visitBoolean,
                Codec.BOOL,
                b -> b ? 1 : 0,
                defaultValue,
                FeatureFlagSet.of()
        );
        GAME_RULES.register(name, () -> rule);
        return rule;
        *///? }
    }

    public static void init() {}
}
