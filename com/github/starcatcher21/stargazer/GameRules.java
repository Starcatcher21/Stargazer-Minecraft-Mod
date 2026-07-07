package com.github.starcatcher21.stargazer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;

public class GameRules {
    public static final GameRule<Boolean> DASH = GameRuleBuilder
            .forBoolean(true) //default value declaration
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.of(Stargazer.MOD_ID, "allowdashing"));

    public static final GameRule<Boolean> MOON = GameRuleBuilder
            .forBoolean(true) //default value declaration
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.of(Stargazer.MOD_ID, "showmoonphaseinrei"));
    public static void init() {}
}
