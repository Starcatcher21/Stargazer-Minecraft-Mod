package com.github.starcatcher21.stargazer.nbt;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
//? if >= 26.2 {
import net.minecraft.core.component.DataComponentGetter;
//? }
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

public record StarPatternsComponent(List<Layer> layers) implements TooltipProvider {
    public static final StarPatternsComponent DEFAULT = new StarPatternsComponent(List.of());
    public static final Codec<StarPatternsComponent> CODEC = Layer.CODEC
            .listOf()
            .xmap(StarPatternsComponent::new, StarPatternsComponent::layers);
    public static final StreamCodec<RegistryFriendlyByteBuf, StarPatternsComponent> PACKET_CODEC = Layer.PACKET_CODEC
            .apply(ByteBufCodecs.list())
            .map(StarPatternsComponent::new, StarPatternsComponent::layers);

    public StarPatternsComponent withoutTopLayer() {
        return new StarPatternsComponent(List.copyOf(this.layers.subList(0, this.layers.size() - 1)));
    }

    //? if >= 1.21.5 {
    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> textConsumer,
                         TooltipFlag type, DataComponentGetter components) {
        for (int i = 0; i < Math.min(this.layers().size(), 6); i++) {
            textConsumer.accept(((Layer)this.layers().get(i)).getTooltipText().withStyle(ChatFormatting.GRAY));
        }
    }
    //? } else {
    /*@Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> textConsumer,
                             TooltipFlag type) {
        for (int i = 0; i < Math.min(this.layers().size(), 6); i++) {
            textConsumer.accept(((Layer)this.layers().get(i)).getTooltipText().withStyle(ChatFormatting.GRAY));
        }
    }
    *///? }
    public MutableComponent getTooltip() {
            return ((Layer)this.layers().getFirst()).getTooltipText().withStyle(ChatFormatting.AQUA);
    }

    public static class Builder {
        private final ImmutableList.Builder<Layer> entries = ImmutableList.builder();

        public Builder add(StarPattern pattern, DyeColor color) {
            return this.add(new Layer(pattern, color));
        }

        public Builder add(Layer layer) {
            this.entries.add(layer);
            return this;
        }

        public Builder addAll(StarPatternsComponent patterns) {
            this.entries.addAll(patterns.layers);
            return this;
        }

        public StarPatternsComponent build() {
            return new StarPatternsComponent(this.entries.build());
        }
    }

    public record Layer(StarPattern pattern, DyeColor color) {
        public static final Codec<Layer> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                StarPattern.CODEC.fieldOf("pattern").forGetter(Layer::pattern),
                                DyeColor.CODEC.fieldOf("color").forGetter(Layer::color)
                        )
                        .apply(instance, Layer::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, Layer> PACKET_CODEC = StreamCodec.composite(
                StarPattern.PACKET_CODEC,
                Layer::pattern,
                DyeColor.STREAM_CODEC,
                Layer::color,
                Layer::new
        );

        public MutableComponent getTooltipText() {
            String string = this.pattern.translationKey();
            return Component.translatable(string);
        }
    }
}
