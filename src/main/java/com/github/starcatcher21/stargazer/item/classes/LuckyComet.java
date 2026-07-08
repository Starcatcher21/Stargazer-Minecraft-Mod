package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import java.util.List;

public class LuckyComet extends Item {
    public LuckyComet(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (!user.hasInfiniteMaterials()) {
            user.getItemInHand(hand).shrink(1);
        }
        ResourceKey<LootTable> customLootKey = ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "gameplay/comet")
        );
        MinecraftServer server = world.getServer();
        if (server != null) {
            LootTable lootTable = server.reloadableRegistries().getLootTable(customLootKey);

            // Create the context parameters using ServerWorld
            LootParams contextSet = new LootParams.Builder((ServerLevel) world)
                    .withParameter(LootContextParams.ORIGIN, user.position())
                    .withParameter(LootContextParams.THIS_ENTITY, user)
                    .create(LootContextParamSets.CHEST);

            // Generate and spawn the items
            List<ItemStack> list = lootTable.getRandomItems(contextSet);
            for (ItemStack item : list) {
                world.addFreshEntity(new ItemEntity(world, user.getX(), user.getY(), user.getZ(), item));
            }
        }
        user.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.CONSUME;
    }
}
