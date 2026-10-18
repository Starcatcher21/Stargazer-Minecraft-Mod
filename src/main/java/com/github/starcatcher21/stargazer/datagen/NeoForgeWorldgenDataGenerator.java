//? if neoforge {
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public final class NeoForgeWorldgenDataGenerator {
    private NeoForgeWorldgenDataGenerator() {}

    //? if >= 26.2 {
    public static void gatherServerData(GatherDataEvent.Server event) {
        gatherData(event);
    }

    public static void gatherClientData(GatherDataEvent.Client event) {
        event.addProvider(new ModelProvider(event.getGenerator().getPackOutput()));
        event.addProvider(new com.github.starcatcher21.stargazer.datagen.lang.ModEngLangProvider(
                event.getGenerator().getPackOutput()
        ));
        event.addProvider(new EquipmentAssetProvider(
                event.getGenerator().getPackOutput(),
                event.getLookupProvider()
        ));
    }
    //?}

    public static void gatherData(GatherDataEvent event) {
        //? if >= 26.2 {
        if (event instanceof GatherDataEvent.Server) {
            DatapackBuiltinEntriesProvider worldgenProvider = new DatapackBuiltinEntriesProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider(),
                    WorldgenDataBootstrap.createRegistrySetBuilder(),
                    Set.of(Stargazer.MOD_ID)
            );
            event.addProvider(worldgenProvider);
            event.addProvider(new BlockTagProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider()
            ));
            event.addProvider(new ItemTagProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider()
            ));
            event.addProvider(new FluidTagProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider()
            ));
            event.addProvider(new POITagProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider()
            ));
            event.addProvider(new FallingObjectProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider()
            ));
            event.addProvider(new FallingObjectListProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider()
            ));
            event.addProvider(new CobbleGenProvider(
                    event.getGenerator().getPackOutput(),
                    event.getLookupProvider()
            ));
            event.addProvider(new net.minecraft.data.loot.LootTableProvider(
                    event.getGenerator().getPackOutput(),
                    Set.of(),
                    List.of(
                            new SubProviderEntry(LootTableProvider::new, LootContextParamSets.BLOCK),
                            new SubProviderEntry(FishingLootPrivider::new, LootContextParamSets.FISHING),
                            new SubProviderEntry(ChestLootPrivider::new, LootContextParamSets.CHEST),
                            new SubProviderEntry(EntityLootTableProvider::new, LootContextParamSets.ENTITY)
                    ),
                    event.getLookupProvider()
            ));
            //? if >= 26.2 {
            event.addProvider(new AdvancementsProvider(
                    event.getGenerator().getPackOutput(),
                    worldgenProvider.getRegistryProvider()
            ));
            //?}
            //? if >= 26.2 {
            event.addProvider(new RecipeProvider(
                    event.getGenerator().getPackOutput(),
                    worldgenProvider.getRegistryProvider()
            ));
            //?}
        }
        //?} else {
        DatapackBuiltinEntriesProvider worldgenProvider = new DatapackBuiltinEntriesProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider(),
                        WorldgenDataBootstrap.createRegistrySetBuilder(),
                        Set.of(Stargazer.MOD_ID)
                );
        event.getGenerator().addProvider(event.includeServer(), worldgenProvider);
        //?}

        //? if < 26.2 {
        event.getGenerator().addProvider(
                event.includeServer(),
                new BlockTagProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider(),
                        event.getExistingFileHelper()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new ItemTagProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider(),
                        event.getExistingFileHelper()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new FluidTagProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider(),
                        event.getExistingFileHelper()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new POITagProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider(),
                        event.getExistingFileHelper()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new FallingObjectProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new FallingObjectListProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new CobbleGenProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new net.minecraft.data.loot.LootTableProvider(
                        event.getGenerator().getPackOutput(),
                        Set.of(),
                        List.of(
                                new SubProviderEntry(
                                        registries -> new LootTableProvider(registries),
                                        LootContextParamSets.BLOCK
                                ),
                                new SubProviderEntry(FishingLootPrivider::new, LootContextParamSets.FISHING),
                                new SubProviderEntry(ChestLootPrivider::new, LootContextParamSets.CHEST),
                                new SubProviderEntry(EntityLootTableProvider::new, LootContextParamSets.ENTITY)
                        ),
                        event.getLookupProvider()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new RecipeProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider()
                )
        );
        event.getGenerator().addProvider(
                event.includeClient(),
                new LegacyNeoForgeModelProvider(
                        event.getGenerator().getPackOutput(),
                        event.getExistingFileHelper()
                )
        );
        event.getGenerator().addProvider(
                event.includeClient(),
                new com.github.starcatcher21.stargazer.datagen.lang.ModEngLangProvider(
                        event.getGenerator().getPackOutput()
                )
        );
        event.getGenerator().addProvider(
                event.includeServer(),
                new AdvancementsProvider(
                        event.getGenerator().getPackOutput(),
                        worldgenProvider.getRegistryProvider()
                )
        );
        //?}
    }
}
//?}
