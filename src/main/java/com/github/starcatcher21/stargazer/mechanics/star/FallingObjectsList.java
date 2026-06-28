package com.github.starcatcher21.stargazer.mechanics.star;

import com.github.starcatcher21.stargazer.RegistryKeys;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FallingObjectsList {
    public static final Codec<FallingObjectsList> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            World.CODEC.fieldOf("world").forGetter(FallingObjectsList::getWorld),
            RegistryFixedCodec.of(RegistryKeys.FALLING_OBJECTS).listOf()
                    .fieldOf("objects")
                    .forGetter(FallingObjectsList::getIdList),
            Codecs.POSITIVE_INT.listOf().fieldOf("chances").forGetter(FallingObjectsList::getChanceList),
            Codecs.NON_NEGATIVE_INT.optionalFieldOf("light").forGetter(FallingObjectsList::getLightLevel),
            FallingObjectDayState.CODEC.optionalFieldOf("daystate").forGetter(FallingObjectsList::getDayState)
    ).apply(instance, FallingObjectsList::new));
    public static final PacketCodec<RegistryByteBuf, FallingObjectsList> PACKET_CODEC = PacketCodecs.registryCodec(CODEC);

    public static List<FallingObjectsList> list2 = new ArrayList<>();

    public List<RegistryEntry<FallingObject>> idList;
    public List<FallingObject> list;
    public RegistryKey<World> world;
    public List<Integer> chanceList;
    public List<FallingObject> weightedList;
    public int lightLevel = 15;
    public FallingObjectDayState dayState = FallingObjectDayState.Both;

    public FallingObjectsList(RegistryKey<World> world, List<RegistryEntry<FallingObject>> idList, List<Integer> chanceList, Optional<Integer> light, Optional<FallingObjectDayState> dstate) {
        this.idList = idList;
        this.list = idList.stream().map(sas -> sas.value()).toList();

        this.world = world;
        this.chanceList = chanceList;
        this.weightedList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            FallingObject cur = list.get(i);
            if (i > chanceList.size()) {
                this.weightedList.add(cur);
            } else {
                Integer a = chanceList.get(i);
                for (int w = 0; w < a; w++) {
                    this.weightedList.add(cur);
                }
            }
        }
        light.ifPresent(integer -> this.lightLevel = integer);
        dstate.ifPresent(s -> this.dayState = s);
        list2.add(this);
    }

    private List<RegistryEntry<FallingObject>> getIdList() {
        return this.idList;
    }
    private List<FallingObject> getList() {
        return this.list;
    }
    private RegistryKey<World> getWorld() {
        return this.world;
    }
    private List<Integer> getChanceList() {
        return this.chanceList;
    }
    private Optional<Integer> getLightLevel() {
        return Optional.of(this.lightLevel);
    }
    private Optional<FallingObjectDayState> getDayState() {
        return Optional.of(this.dayState);
    }

}
