package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class DamageTypeRegistry {
    public static final RegistryKey<DamageType> WATER_DAMAGE = register("water");
    public static final RegistryKey<DamageType> GLASS_CANNON = register("glasscannon");
    public static final RegistryKey<DamageType> STAR_TRAP = register("star_trap");
    public static RegistryKey<DamageType> register(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Stargazer.MOD_ID, id));
    }
    public static void init() {}
}
