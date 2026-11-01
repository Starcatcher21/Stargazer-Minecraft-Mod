package com.github.starcatcher21.stargazer.compat.jei;

//? if >= 26.2 {
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.item.crafting.RecipeType;
import java.util.List;
//? if fabric {
import net.fabricmc.fabric.api.recipe.v1.FabricRecipeAccess;
//?}
//? if neoforge {
/*import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
*///?}
//?}

/**
 * Client-side access to the mod's recipes (26.2+).
 * Vanilla no longer sends recipes to the client, so each loader needs its own route:
 * Fabric reads them through Fabric API's FabricRecipeAccess (types must be registered with
 * RecipeSynchronization.synchronizeRecipeSerializer in your common/mod initializer),
 * NeoForge caches the RecipeMap from RecipesReceivedEvent (types requested via OnDatapackSyncEvent#sendRecipes).
 */
public final class ClientRecipes {

    private ClientRecipes() {}

    //? if >= 26.2 {
    //? if fabric {
    public static <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> all(RecipeType<T> type) {
        var level = Minecraft.getInstance().level;
        if (level == null) {
            return List.of();
        }
        return List.copyOf(((FabricRecipeAccess) level.recipeAccess()).getSynchronizedRecipes().getAllOfType(type));
    }
    //?}

    //? if neoforge {
    /*private static RecipeMap recipeMap = RecipeMap.EMPTY;

    public static <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> all(RecipeType<T> type) {
        return List.copyOf(recipeMap.byType(type));
    }

    // Runs on both logical sides: tells the server which recipe types to send.
    @EventBusSubscriber(modid = Stargazer.MOD_ID)
    public static final class Sync {
        @SubscribeEvent
        public static void onDatapackSync(OnDatapackSyncEvent event) {
            event.sendRecipes(
                    RecipeTypes.STAR_CRUSHER.get(),
                    RecipeTypes.MOON_WELDER.get(),
                    RecipeTypes.STARFORGE.get());
        }
    }

    @EventBusSubscriber(modid = Stargazer.MOD_ID, value = Dist.CLIENT)
    public static final class Receive {
        @SubscribeEvent
        public static void onRecipes(RecipesReceivedEvent event) {
            recipeMap = event.getRecipeMap();
        }

        @SubscribeEvent
        public static void onLogout(ClientPlayerNetworkEvent.LoggingOut event) {
            recipeMap = RecipeMap.EMPTY;
        }
    }
    *///?}
    //?}
}
