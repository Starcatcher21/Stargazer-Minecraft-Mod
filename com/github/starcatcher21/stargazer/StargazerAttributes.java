package com.github.starcatcher21.stargazer;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class StargazerAttributes {
    public static final RegistryEntry<EntityAttribute> DASH_LEVEL = registerCustom(Stargazer.MOD_ID,
            "dash_level", new ClampedEntityAttribute("attribute.name.gsad.dash_level", 0.0, 0.0, Integer.MAX_VALUE).setTracked(true)
    );

    private static RegistryEntry<EntityAttribute> registerCustom(String id,String name, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(id, name), attribute);
    }
    public static void init() {}
}