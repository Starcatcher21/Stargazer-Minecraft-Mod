package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.datagen.ModFluidTagProvider;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// TODO(Ravel): can not resolve target class FishingBobberEntity
// TODO(Ravel): can not resolve target class FishingBobberEntity
@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"
            )
    )
    private boolean allowCustomFluidFishing(FluidState fluidState, TagKey<Fluid> tag) {
        // If Minecraft is checking for water, also allow our custom fluid
        return fluidState.isIn(tag) || fluidState.isIn(ModFluidTagProvider.DREAM);
    }
    // TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Redirect(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/ReloadableRegistries$Lookup;getLootTable(Lnet/minecraft/registry/RegistryKey;)Lnet/minecraft/loot/LootTable;"
            )
    )
    private LootTable redirectFishingLoot(net.minecraft.registry.ReloadableRegistries.Lookup lookup, RegistryKey<LootTable> vanillaKey) {
        FishingBobberEntity bobber = (FishingBobberEntity) (Object) this;

        if (bobber.getEntityWorld().getFluidState(bobber.getBlockPos()).isIn(ModFluidTagProvider.DREAM)) {
            RegistryKey<LootTable> customLootKey = RegistryKey.of(
                    RegistryKeys.LOOT_TABLE,
                    Identifier.of(Stargazer.MOD_ID, "gameplay/fishing/dream")
            );
            return lookup.getLootTable(customLootKey);
        }
        // Fallback to vanilla water fishing loot
        return lookup.getLootTable(vanillaKey);
    }
}