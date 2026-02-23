package com.github.starcatcher21.stargazer.mechanics.star;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerDataLoader;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FallingObjectsList {
    public static final Codec<FallingObjectsList> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            World.CODEC.fieldOf("world").forGetter(FallingObjectsList::getWorld),
            Identifier.CODEC.listOf().fieldOf("objects").forGetter(FallingObjectsList::getIdList),
            Codecs.POSITIVE_INT.listOf().fieldOf("chances").forGetter(FallingObjectsList::getChanceList),
            Codecs.POSITIVE_INT.optionalFieldOf("light").forGetter(FallingObjectsList::getLightLevel),
            FallingObjectDayState.CODEC.optionalFieldOf("daystate").forGetter(FallingObjectsList::getDayState)
    ).apply(instance, FallingObjectsList::new));


    public List<Identifier> idList;
    public List<FallingObject> list;
    public RegistryKey<World> world;
    public List<Integer> chanceList;
    public List<FallingObject> weightedList;
    public int lightLevel = 15;
    public FallingObjectDayState dayState = FallingObjectDayState.Both;

    public FallingObjectsList(RegistryKey<World> world, List<Identifier> idList, List<Integer> chanceList, Optional<Integer> light, Optional<FallingObjectDayState> dstate) {
        this.idList = idList;
        this.list = new ArrayList<>();

        Map<Identifier, FallingObject> map = StargazerDataLoader.getFallingObjectData();
        for (Identifier id : idList) {
            if (map.containsKey(id)) {
                this.list.add(map.get(id));
            } else {
                Stargazer.LOGGER.warn("There's no Falling Object with id: " + id);
            }
        }
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
    }

    private List<Identifier> getIdList() {
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
