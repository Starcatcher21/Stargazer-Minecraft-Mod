package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.BiConsumer;

@Mixin(Sniffer.class)
public class SnifferMixin {
    private static final ResourceKey<LootTable> MOON_ROCK_LOOT =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "sniffer_moon")
            );

    //? if >=26.2 {
    @Redirect(
            method = "dropSeed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/sniffer/Sniffer;dropFromGiftLootTable(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/resources/ResourceKey;Ljava/util/function/BiConsumer;)Z"
            )
    )
    private boolean stargazer$redirectGiftLoot(
            Sniffer instance, ServerLevel serverLevel, ResourceKey resourceKey, BiConsumer biConsumer
    ) {
        BlockPos head = ((SnifferAccessor) instance).invokeGetHeadBlock();


        return instance.dropFromGiftLootTable(serverLevel, stargazer$getLootTableKey(serverLevel, head, resourceKey), biConsumer);
    }
    //?} else {
    /*@ModifyArg(
        method = "dropSeed",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/ReloadableServerRegistries$Holder;getLootTable(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/level/storage/loot/LootTable;"),
        index = 0
    )
    private ResourceKey<LootTable> stargazer$moonRockLoot(ResourceKey<LootTable> original) {
        Sniffer self = (Sniffer)(Object)this;
        BlockPos head = ((SnifferAccessor)this).invokeGetHeadBlock();
        return stargazer$getLootTableKey(self.level(), head, original);
    }
    *///?}
    @Unique
    private ResourceKey<LootTable> stargazer$getLootTableKey(Level level, BlockPos head, ResourceKey<LootTable> origin) {
        if (level.getBlockState(head.below()).is(MoonBlocks.MOON_ROCK_NYLIUM.get())) return MOON_ROCK_LOOT;
        return origin;
    }
}
