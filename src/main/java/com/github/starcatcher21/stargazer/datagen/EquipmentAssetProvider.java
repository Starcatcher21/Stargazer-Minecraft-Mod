/*? if fabric {*/
/*package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.armor.EquipmentAsset;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public EquipmentAssetProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(EquipmentAsset.MASK_OF_LUNA,
                EquipmentClientInfo.builder()
                        .addHumanoidLayers(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "mask_of_luna"))
                        .build());
        consumer.accept(EquipmentAsset.JESTER,
                EquipmentClientInfo.builder()
                        .addHumanoidLayers(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "jester"))
                        .build());
        consumer.accept(EquipmentAsset.MOON,
                EquipmentClientInfo.builder()
                        .addHumanoidLayers(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "moon"))
                        .build());
        consumer.accept(EquipmentAsset.AMETHYST,
                EquipmentClientInfo.builder()
                        .addHumanoidLayers(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "amethyst"))
                        .build());
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset>, EquipmentClientInfo> equipmentAsstes = new HashMap<>();
        bootstrap((id, asset) -> {
            if (equipmentAsstes.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to init equipment asset twice for id: " + id);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAsstes);
    }

    @Override
    public String getName() {
        return "Stargazer Equipment Asset Definitions";
    }
}
*///? }
