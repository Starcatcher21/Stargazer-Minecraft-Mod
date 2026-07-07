package com.github.starcatcher21.stargazer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class GameRules {
    public static final GameRule<Boolean> DASH = GameRuleBuilder
            .forBoolean(true) //default value declaration
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "allowdashing"));

    public static final GameRule<Boolean> MOON = GameRuleBuilder
            .forBoolean(true) //default value declaration
            .category(GameRuleCategory.MISC)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "showmoonphaseinrei"));
    public static void init() {}
}
