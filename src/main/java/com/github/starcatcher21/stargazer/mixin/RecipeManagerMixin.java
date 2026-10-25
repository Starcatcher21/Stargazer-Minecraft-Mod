package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.datagen.RecipeDataBootstrap;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonElement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    //? if < 26.2 {
    /*@Shadow @Final @Mutable
    private Map<Identifier, RecipeHolder<?>> byName;

    @Shadow
    @Final
    private HolderLookup.Provider registries;

    @Shadow @Final @Mutable
    private Multimap<RecipeType<?>, RecipeHolder<?>> byType;

    @Inject(method = "apply", at = @At("TAIL"))
    private void stargazer$injectRecipes(Map<Identifier, JsonElement> recipes,
                                         ResourceManager resourceManager,
                                         ProfilerFiller profiler,
                                         CallbackInfo ci) {
        Map<Identifier, Recipe<?>> extra =
                RecipeDataBootstrap.loadAll(this.registries);
        if (extra.isEmpty()) return;

        Map<Identifier, RecipeHolder<?>> newByName = new HashMap<>(this.byName);
        Multimap<RecipeType<?>, RecipeHolder<?>> newByType =
                ArrayListMultimap.create(this.byType);

        extra.forEach((id, recipe) -> {
            RecipeHolder<Recipe<?>> holder = new RecipeHolder<>(id, recipe);
            newByName.put(id, holder);
            newByType.put(recipe.getType(), holder);
        });

        this.byName = newByName;
        this.byType = newByType;
    }
    *///?}
}
