package com.github.starcatcher21.stargazer;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class StargazerAttributes {
    public static final Holder<Attribute> DASH_LEVEL = registerCustom(Stargazer.MOD_ID,
            "dash_level", new RangedAttribute("attribute.name.gsad.dash_level", 0.0, 0.0, Integer.MAX_VALUE).setSyncable(true)
    );

    private static Holder<Attribute> registerCustom(String id,String name, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(id, name), attribute);
    }
    public static void init() {}
}