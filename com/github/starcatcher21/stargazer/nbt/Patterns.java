package com.github.starcatcher21.stargazer.nbt;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Patterns {
    public static final Codec<Patterns> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("asset_id").forGetter(Patterns::getPattern),
            Codec.list(Item.ENTRY_CODEC).fieldOf("items").forGetter(Patterns::getItems)
    ).apply(instance, Patterns::new));
    public static final PacketCodec<RegistryByteBuf, Patterns> PACKET_CODEC = PacketCodec.tuple(
            Identifier.PACKET_CODEC,
            Patterns::getPattern,
            Item.ENTRY_PACKET_CODEC.collect(PacketCodecs.toList()),
            Patterns::getItems,
            Patterns::new
    );
    public Identifier pattern;
    public List<RegistryEntry<Item>> items2;
    public Patterns(Identifier pattern, List<RegistryEntry<Item>> items2) {
        this.pattern = pattern;
        this.items2 = items2;
        of(pattern, items2);
    }
    public Identifier getPattern() { return pattern; }
    public List<RegistryEntry<Item>> getItems() { return items2; }
    public static List<StarPattern> patternList = new ArrayList<>();
    public static Map<StarPattern, List<Item>> itemList = new HashMap<>();

    public static StarPattern of(Identifier id, List<RegistryEntry<Item>> items) {
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
