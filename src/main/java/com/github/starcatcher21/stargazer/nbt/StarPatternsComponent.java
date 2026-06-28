package com.github.starcatcher21.stargazer.nbt;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.function.Consumer;

public record StarPatternsComponent(List<StarPatternsComponent.Layer> layers) implements TooltipAppender {
    public static final StarPatternsComponent DEFAULT = new StarPatternsComponent(List.of());
    public static final Codec<StarPatternsComponent> CODEC = StarPatternsComponent.Layer.CODEC
            .listOf()
            .xmap(StarPatternsComponent::new, StarPatternsComponent::layers);
    public static final PacketCodec<RegistryByteBuf, StarPatternsComponent> PACKET_CODEC = StarPatternsComponent.Layer.PACKET_CODEC
            .collect(PacketCodecs.toList())
            .xmap(StarPatternsComponent::new, StarPatternsComponent::layers);

    public StarPatternsComponent withoutTopLayer() {
        return new StarPatternsComponent(List.copyOf(this.layers.subList(0, this.layers.size() - 1)));
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        for (int i = 0; i < Math.min(this.layers().size(), 6); i++) {
            textConsumer.accept(((StarPatternsComponent.Layer)this.layers().get(i)).getTooltipText().formatted(Formatting.GRAY));
        }
    }
    public MutableText getTooltip() {
            return ((StarPatternsComponent.Layer)this.layers().getFirst()).getTooltipText().formatted(Formatting.AQUA);
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
        public static final PacketCodec<RegistryByteBuf, StarPatternsComponent.Layer> PACKET_CODEC = PacketCodec.tuple(
                StarPattern.PACKET_CODEC,
                StarPatternsComponent.Layer::pattern,
                DyeColor.PACKET_CODEC,
                StarPatternsComponent.Layer::color,
                StarPatternsComponent.Layer::new
        );

        public MutableText getTooltipText() {
            String string = this.pattern.translationKey();
            return net.minecraft.text.Text.translatable(string);
        }
    }
}
