package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ModItems;
import dev.architectury.core.block.ArchitecturyLiquidBlock;
import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;
import dev.architectury.core.item.ArchitecturyBucketItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Stargazer.MOD_ID, Registries.FLUID);

    public static final RegistrySupplier<ArchitecturyFlowingFluid.Source> DREAM_STILL =
            FLUIDS.register("dream", () -> new ArchitecturyFlowingFluid.Source(Holder.DREAM_ATTRIBUTES));

    public static final RegistrySupplier<ArchitecturyFlowingFluid.Flowing> DREAM_FLOWING =
            FLUIDS.register("flowing_dream", () -> new ArchitecturyFlowingFluid.Flowing(Holder.DREAM_ATTRIBUTES));

    public static final RegistrySupplier<LiquidBlock> DREAM_BLOCK = ModBlock.BLOCKS.register("dream", () -> {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "dream"));
        return new ArchitecturyLiquidBlock(DREAM_STILL, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                //? if >= 26.2 {
                /*.setId(key)
                *///? }
        );
    });

    public static final RegistrySupplier<Item> DREAM_BUCKET = ModItems.ITEMS.register("dream_bucket", () -> {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "dream_bucket"));
        return new ArchitecturyBucketItem(DREAM_STILL, new Item.Properties().craftRemainder(Items.BUCKET)
                //? if >= 26.2 {
                /*.setId(key)
                *///? }
        );
    });

    public static class Holder {
        public static final ArchitecturyFluidAttributes DREAM_ATTRIBUTES =
                SimpleArchitecturyFluidAttributes.of(() -> DREAM_FLOWING.get(), () -> DREAM_STILL.get())
                        .sourceTexture(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "fluid/dream_still"))
                        .flowingTexture(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "fluid/dream_flow"))
                        .color(0xFF1A1A1A)
                        .density(25)
                        .viscosity(25)
                        .luminosity(4)
                        .blockSupplier(() -> DREAM_BLOCK)
                        .bucketItemSupplier(() -> DREAM_BUCKET);
    }

    public static void init() {}
}
