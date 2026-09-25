package com.github.starcatcher21.stargazer;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class GameRules {
    public static final GameRule<Boolean> DASH = register("allowdashing", true, GameRuleCategory.PLAYER);
    public static final GameRule<Boolean> MOON = register("showmoonphaseinrei", true, GameRuleCategory.MISC);

    @ExpectPlatform
    public static GameRule<Boolean> register(String name, boolean defaultValue, GameRuleCategory category) {
        throw new AssertionError();
    }

    public static void init() {}
}