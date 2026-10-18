package com.github.starcatcher21.stargazer;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
//? if neoforge {
import net.neoforged.bus.api.IEventBus;
//?}
//? if neoforge {
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
//?}

public class StargazerAttributes {
    //? if neoforge {
    private static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, Stargazer.MOD_ID);
    public static final DeferredHolder<Attribute, Attribute> DASH_LEVEL =
            ATTRIBUTES.register("dash_level", () ->
                    new RangedAttribute("attribute.name.stargazer.dash_level", 1.0, 0.0, Integer.MAX_VALUE)
                            .setSyncable(true));
    //?} else {
    public static final Holder<Attribute> DASH_LEVEL = registerCustom(
            Stargazer.MOD_ID,
            "dash_level",
            new RangedAttribute("attribute.name.stargazer.dash_level", 1.0, 0.0, Integer.MAX_VALUE).setSyncable(true)
    );
    //?}

    //? if neoforge {
    public static void init(IEventBus modEventBus) {
        ATTRIBUTES.register(modEventBus);
    }
    //?}

    //? if >= 26.2 {
    private static Holder<Attribute> registerCustom(String id, String name, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE,
                ResourceLocation.fromNamespaceAndPath(id, name), attribute);
    }
    //?}

    public static void init() {}
}
