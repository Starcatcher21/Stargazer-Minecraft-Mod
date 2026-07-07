package com.github.starcatcher21.stargazer.nbt;

import com.github.starcatcher21.stargazer.Stargazer;
import java.util.function.UnaryOperator;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ComponentTypes {
    public static final DataComponentType<StarPatternsComponent> STAR_PATTERNS = register(
            "star_patterns", builder -> builder.persistent(StarPatternsComponent.CODEC).networkSynchronized(StarPatternsComponent.PACKET_CODEC).cacheEncoding()
    );

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        // Notice we cast to the specific Builder<T> or, better yet, avoid the cast entirely if possible
        DataComponentType.Builder<T> builder = builderOperator.apply(DataComponentType.builder());
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id), builder.build());
    }
    public static void init() {}
}