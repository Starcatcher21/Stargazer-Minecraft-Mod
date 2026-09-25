package com.github.starcatcher21.stargazer.nbt;

import com.github.starcatcher21.stargazer.Stargazer;
import java.util.function.UnaryOperator;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

public class ComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<StarPatternsComponent>> STAR_PATTERNS =
            DATA_COMPONENTS.register("star_patterns", () -> DataComponentType.<StarPatternsComponent>builder()
                    .persistent(StarPatternsComponent.CODEC)
                    .networkSynchronized(StarPatternsComponent.PACKET_CODEC)
                    .cacheEncoding()
                    .build()
            );

    public static void init() {
        DATA_COMPONENTS.register();
    }
}