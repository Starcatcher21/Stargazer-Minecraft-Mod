package com.github.starcatcher21.stargazer.nbt;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ComponentTypes {
    public static final ComponentType<StarPatternsComponent> STAR_PATTERNS = register(
            "star_patterns", builder -> builder.codec(StarPatternsComponent.CODEC).packetCodec(StarPatternsComponent.PACKET_CODEC).cache()
    );

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        // Notice we cast to the specific Builder<T> or, better yet, avoid the cast entirely if possible
        ComponentType.Builder<T> builder = builderOperator.apply(ComponentType.builder());
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Stargazer.MOD_ID, id), builder.build());
    }
    public static void init() {}
}