package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class LuckyComet extends Item {
    public LuckyComet(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!user.isInCreativeMode()) {
            user.getStackInHand(hand).decrement(1);
        }
        RegistryKey<LootTable> customLootKey = RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                Identifier.of(Stargazer.MOD_ID, "gameplay/comet")
        );
        MinecraftServer server = world.getServer();
        if (server != null) {
            LootTable lootTable = server.getReloadableRegistries().getLootTable(customLootKey);

            // Create the context parameters using ServerWorld
            LootWorldContext contextSet = new LootWorldContext.Builder((ServerWorld) world)
                    .add(LootContextParameters.ORIGIN, user.getEntityPos())
                    .add(LootContextParameters.THIS_ENTITY, user)
                    .build(LootContextTypes.CHEST);

            // Generate and spawn the items
            List<ItemStack> list = lootTable.generateLoot(contextSet);
            for (ItemStack item : list) {
                world.spawnEntity(new ItemEntity(world, user.getX(), user.getY(), user.getZ(), item));
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return ActionResult.CONSUME;
    }
}
