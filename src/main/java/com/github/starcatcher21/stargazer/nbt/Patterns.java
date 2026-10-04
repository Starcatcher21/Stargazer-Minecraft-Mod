package com.github.starcatcher21.stargazer.nbt;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class Patterns {
    public static final Codec<Patterns> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(Patterns::getPattern),
            Codec.list(Item.CODEC).fieldOf("items").forGetter(Patterns::getItems)
    ).apply(instance, Patterns::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, Patterns> PACKET_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            Patterns::getPattern,
            Item.STREAM_CODEC.apply(ByteBufCodecs.list()),
            Patterns::getItems,
            Patterns::new
    );
    public ResourceLocation pattern;
    public List<Holder<Item>> items2;
    public Patterns(ResourceLocation pattern, List<Holder<Item>> items2) {
        this.pattern = pattern;
        this.items2 = items2;
        of(pattern, items2);
    }
    public ResourceLocation getPattern() { return pattern; }
    public List<Holder<Item>> getItems() { return items2; }
    public static List<StarPattern> patternList = new ArrayList<>();
    public static Map<StarPattern, List<Item>> itemList = new HashMap<>();

    public static StarPattern of(ResourceLocation id, List<Holder<Item>> items) {
        StarPattern pat = new StarPattern(id, id.getNamespace() + ".star_pattern." + id.getPath());
        try {
            patternList.add(pat);
            List<Item> listtttt = items.stream().map(itemRegistryEntry -> itemRegistryEntry.value()).toList();
            itemList.put(pat, listtttt);
        } catch (Exception ignored) {}
        return pat;
    }

    public static void init() {
    }
}
