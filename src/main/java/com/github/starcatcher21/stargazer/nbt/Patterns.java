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
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class Patterns {
    public static final Codec<Patterns> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("asset_id").forGetter(Patterns::getPattern),
            Codec.list(Item.CODEC).fieldOf("items").forGetter(Patterns::getItems)
    ).apply(instance, Patterns::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, Patterns> PACKET_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC,
            Patterns::getPattern,
            Item.STREAM_CODEC.apply(ByteBufCodecs.list()),
            Patterns::getItems,
            Patterns::new
    );
    public Identifier pattern;
    public List<Holder<Item>> items2;
    public Patterns(Identifier pattern, List<Holder<Item>> items2) {
        this.pattern = pattern;
        this.items2 = items2;
        of(pattern, items2);
    }
    public Identifier getPattern() { return pattern; }
    public List<Holder<Item>> getItems() { return items2; }
    public static List<StarPattern> patternList = new ArrayList<>();
    public static Map<StarPattern, List<Item>> itemList = new HashMap<>();

    public static StarPattern of(Identifier id, List<Holder<Item>> items) {
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
