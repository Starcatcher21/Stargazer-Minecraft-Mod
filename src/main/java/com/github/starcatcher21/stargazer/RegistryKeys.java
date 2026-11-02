package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
//? if fabric {
/*import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
*///? }
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import java.util.function.Supplier;
//? if neoforge {
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
//? }

//? if neoforge {
@EventBusSubscriber(modid = Stargazer.MOD_ID)
//? }
public class RegistryKeys {
    //? if neoforge {
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
        ENTITY_DATA_SERIALIZERS.register("patterns_component", () -> RegistryKeys.PATTERN_COMPONENT2);
    }
    //? }

    public static final ResourceKey<Registry<Patterns>> STAR_PATTERN = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "star_pattern"));
    public static final EntityDataSerializer<StarPatternsComponent> PATTERN_COMPONENT2 = EntityDataSerializer.forValueType(StarPatternsComponent.PACKET_CODEC);

    public static void init() {
        //? if fabric {
        /*DynamicRegistries.register(
                RegistryKeys.STAR_PATTERN,
                Patterns.CODEC
        );
        FabricEntityDataRegistry.register(
              ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "patterns_component"),
                RegistryKeys.PATTERN_COMPONENT2
        );
        *///? }
    }
    //? if neoforge {
    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event) {
        // Register the dynamic datapack registry equivalent to Fabric's DynamicRegistries
        event.dataPackRegistry(
                RegistryKeys.STAR_PATTERN,
                Patterns.CODEC,
                Patterns.CODEC
        );
    }
    //? }
}
