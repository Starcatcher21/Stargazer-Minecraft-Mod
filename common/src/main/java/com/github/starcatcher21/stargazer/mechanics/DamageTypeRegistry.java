package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypeRegistry {
    public static final ResourceKey<DamageType> WATER_DAMAGE = register("water");
    public static final ResourceKey<DamageType> GLASS_CANNON = register("glasscannon");
    public static final ResourceKey<DamageType> STAR_TRAP = register("star_trap");
    public static ResourceKey<DamageType> register(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id));
    }
    public static void init() {}
}
