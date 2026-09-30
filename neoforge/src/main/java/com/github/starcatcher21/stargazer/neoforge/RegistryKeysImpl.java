package com.github.starcatcher21.stargazer.neoforge;

import com.github.starcatcher21.stargazer.RegistryKeys;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Stargazer.MOD_ID)
public class RegistryKeysImpl {

    // Create a DeferredRegister for Entity Data Serializers in NeoForge
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, Stargazer.MOD_ID);

    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Stargazer.MOD_ID);

    // 2. Register the Data Component Type correctly using registerComponentType
    public static final Supplier<DataComponentType<Patterns>> PATTERN_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
                    "star_patterns",
                    builder -> builder
                            .persistent(Patterns.CODEC)
                            .networkSynchronized(Patterns.PACKET_CODEC) // Omit or adjust if you don't need network sync
            );

    static {
        // Register the entity data serializer entry
        ENTITY_DATA_SERIALIZERS.register("patterns_component", () -> RegistryKeys.PATTERN_COMPONENT);
    }

    public static void init() {
    }

    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event) {
        // Register the dynamic datapack registry equivalent to Fabric's DynamicRegistries
        event.dataPackRegistry(
                RegistryKeys.STAR_PATTERN,
                Patterns.CODEC,
                Patterns.CODEC
        );
    }
}