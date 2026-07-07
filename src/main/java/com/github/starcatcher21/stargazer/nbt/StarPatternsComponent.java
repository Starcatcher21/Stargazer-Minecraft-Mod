// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.nbt;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

public record StarPatternsComponent(List<StarPatternsComponent.Layer> layers) implements TooltipProvider {
    public static final StarPatternsComponent DEFAULT = new StarPatternsComponent(List.of());
    public static final Codec<StarPatternsComponent> CODEC = StarPatternsComponent.Layer.CODEC
            .listOf()
            .xmap(StarPatternsComponent::new, StarPatternsComponent::layers);
    public static final StreamCodec<RegistryFriendlyByteBuf, StarPatternsComponent> PACKET_CODEC = StarPatternsComponent.Layer.PACKET_CODEC
            .apply(ByteBufCodecs.list())
            .map(StarPatternsComponent::new, StarPatternsComponent::layers);

    public StarPatternsComponent withoutTopLayer() {
        return new StarPatternsComponent(List.copyOf(this.layers.subList(0, this.layers.size() - 1)));
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> textConsumer, TooltipFlag type, DataComponentGetter components) {
        for (int i = 0; i < Math.min(this.layers().size(), 6); i++) {
            textConsumer.accept(((StarPatternsComponent.Layer)this.layers().get(i)).getTooltipText().withStyle(ChatFormatting.GRAY));
        }
    }
    public MutableComponent getTooltip() {
            return ((StarPatternsComponent.Layer)this.layers().getFirst()).getTooltipText().withStyle(ChatFormatting.AQUA);
    }

    public static class Builder {
        private final ImmutableList.Builder<StarPatternsComponent.Layer> entries = ImmutableList.builder();

        public StarPatternsComponent.Builder add(StarPattern pattern, DyeColor color) {
            return this.add(new StarPatternsComponent.Layer(pattern, color));
        }

        public StarPatternsComponent.Builder add(StarPatternsComponent.Layer layer) {
            this.entries.add(layer);
            return this;
        }

        public StarPatternsComponent.Builder addAll(StarPatternsComponent patterns) {
            this.entries.addAll(patterns.layers);
            return this;
        }

        public StarPatternsComponent build() {
            return new StarPatternsComponent(this.entries.build());
        }
    }

    public record Layer(StarPattern pattern, DyeColor color) {
        public static final Codec<StarPatternsComponent.Layer> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                StarPattern.CODEC.fieldOf("pattern").forGetter(StarPatternsComponent.Layer::pattern),
                                DyeColor.CODEC.fieldOf("color").forGetter(StarPatternsComponent.Layer::color)
                        )
                        .apply(instance, StarPatternsComponent.Layer::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, StarPatternsComponent.Layer> PACKET_CODEC = StreamCodec.composite(
                StarPattern.PACKET_CODEC,
                StarPatternsComponent.Layer::pattern,
                DyeColor.STREAM_CODEC,
                StarPatternsComponent.Layer::color,
                StarPatternsComponent.Layer::new
        );

        public MutableComponent getTooltipText() {
            String string = this.pattern.translationKey();
            return net.minecraft.network.chat.Component.translatable(string);
        }
    }
}
