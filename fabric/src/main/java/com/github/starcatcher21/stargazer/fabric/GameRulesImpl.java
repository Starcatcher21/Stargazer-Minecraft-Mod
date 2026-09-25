package com.github.starcatcher21.stargazer.fabric;

import com.github.starcatcher21.stargazer.Stargazer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class GameRulesImpl {
    public static GameRule<Boolean> register(String name, boolean defaultValue, GameRuleCategory category) {
        return GameRuleBuilder.forBoolean(defaultValue)
                .category(category)
                .buildAndRegister(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name));
    }
}