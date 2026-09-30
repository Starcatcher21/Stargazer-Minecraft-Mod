package com.github.starcatcher21.stargazer.neoforge;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

public class GameRulesImpl {
    public static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(Stargazer.MOD_ID, Registries.GAME_RULE);

    public static GameRule<Boolean> register(String name, boolean defaultValue, GameRuleCategory category) {
        GameRule<Boolean> rule = new GameRule<>(
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
    }
}