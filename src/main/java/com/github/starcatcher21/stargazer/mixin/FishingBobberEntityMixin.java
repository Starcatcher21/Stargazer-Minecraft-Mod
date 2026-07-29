package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.datagen.FluidTagProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingHook.class)
public class FishingBobberEntityMixin {

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z",
                    ordinal = 0
            )
    )
    private boolean allowDreamFluidHeightCheck(FluidState fluidState, TagKey<Fluid> tag) {
        return isWaterOrDreamFluid(fluidState, tag);
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z",
                    ordinal = 1
            )
    )
    private boolean allowDreamFluidOutOfWaterCheck(FluidState fluidState, TagKey<Fluid> tag) {
        return isWaterOrDreamFluid(fluidState, tag);
    }

    @Redirect(
            method = "getOpenWaterTypeForBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean allowDreamFluidOpenWaterCheck(FluidState fluidState, TagKey<Fluid> tag) {
        return isWaterOrDreamFluid(fluidState, tag);
    }

    @Unique
    private static boolean isWaterOrDreamFluid(FluidState fluidState, TagKey<Fluid> tag) {
        return fluidState.is(tag) || (FluidTags.WATER.equals(tag) && fluidState.is(FluidTagProvider.DREAM));
    }

    @Redirect(
            method = "retrieve",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/ReloadableServerRegistries$Holder;getLootTable(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/level/storage/loot/LootTable;"
            )
    )
    private LootTable redirectDreamFishingLoot(net.minecraft.server.ReloadableServerRegistries.Holder lookup, ResourceKey<LootTable> vanillaKey) {
        FishingHook bobber = (FishingHook) (Object) this;

        if (bobber.level().getFluidState(bobber.blockPosition()).is(FluidTagProvider.DREAM)) {
            ResourceKey<LootTable> customLootKey = ResourceKey.create(
                    Registries.LOOT_TABLE,
                    Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "gameplay/fishing/dream")
            );
            return lookup.getLootTable(customLootKey);
        }

        return lookup.getLootTable(vanillaKey);
    }
}
