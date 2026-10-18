//? if neoforge && <= 1.21.1 {
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Set;

public final class LegacyNeoForgeModelProvider extends BlockStateProvider {
    private static final ExistingFileHelper.ResourceType TEXTURE_RESOURCE =
            new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");
    private static final Set<String> SOURCE_BLOCKSTATES = Set.of(
            "grave", "negative_block", "no_red_block", "no_green_block", "no_blue_block",
            "infested_calcite", "copper_teleporter", "dark_teleporter", "end_teleporter",
            "red_teleporter", "sprinkler", "moon_welder", "moon_farmland", "moon_rock_nylium",
            "moon_rock_tiles", "purple_moon_rock_tiles", "star_forge", "moon_planks_door",
            "star_stone", "geode_fruit_block", "star_trap", "forget_me_now",
            "potted_forget_me_now", "cosmic_block", "star_barrier_block", "border_block",
            "star_leaves", "aurora", "star_display", "eye_log", "eye_jar", "eye_fern",
            "eyes", "gradi", "potted_gradi", "chessboard"
    );
    private final ExistingFileHelper existingFileHelper;

    public LegacyNeoForgeModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Stargazer.MOD_ID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        BuiltInRegistries.BLOCK.stream()
                .filter(block -> isModBlock(block))
                .filter(block -> !SOURCE_BLOCKSTATES.contains(BuiltInRegistries.BLOCK.getKey(block).getPath()))
                .forEach(block -> simpleBlock(block, models().cubeAll(
                        BuiltInRegistries.BLOCK.getKey(block).getPath(),
                        textureFor(block)
                )));

        BuiltInRegistries.ITEM.stream()
                .filter(item -> Stargazer.MOD_ID.equals(BuiltInRegistries.ITEM.getKey(item).getNamespace()))
                .forEach(item -> {
                    if (item instanceof BlockItem blockItem) {
                        if (!SOURCE_BLOCKSTATES.contains(
                                BuiltInRegistries.BLOCK.getKey(blockItem.getBlock()).getPath()
                        )) {
                            itemModels().simpleBlockItem(blockItem.getBlock());
                        }
                    } else {
                        itemModels().basicItem(item);
                    }
                });
    }

    private static boolean isModBlock(Block block) {
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
        return Stargazer.MOD_ID.equals(id.getNamespace());
    }

    private ResourceLocation textureFor(Block block) {
        String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
        ResourceLocation texture = modLoc("block/" + path);
        if (existingFileHelper.exists(texture, TEXTURE_RESOURCE)) {
            return texture;
        }
        if (path.startsWith("potted_")) {
            ResourceLocation plantTexture = modLoc("block/" + path.substring("potted_".length()));
            if (existingFileHelper.exists(plantTexture, TEXTURE_RESOURCE)) {
                return plantTexture;
            }
        }
        return ResourceLocation.parse("minecraft:block/stone");
    }
}
//?}
