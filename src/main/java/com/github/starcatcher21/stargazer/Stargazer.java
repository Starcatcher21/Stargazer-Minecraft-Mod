package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.CreativeTab.ItemGroup;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.energy.EnergyHooks;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ConsumeEffectsRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.PlayerCosmicGrav;
import com.github.starcatcher21.stargazer.mechanics.PlayerRedOrbGrav;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.particle.Particles;
import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.sound.SoundEffects;
import com.github.starcatcher21.stargazer.stats.ModStats;
import com.github.starcatcher21.stargazer.villager.ModTraids;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import com.github.starcatcher21.stargazer.worldgen.CustomFeatures;
import com.github.starcatcher21.stargazer.worldgen.features.PlacedFeatures;
import com.github.starcatcher21.stargazer.worldgen.features.trees.TreesRegistry;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.starcatcher21.stargazer.villager.ModVillagers.init;

//? if fabric {
import com.github.starcatcher21.stargazer.GameRules;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.player.Player;
//?}

//? if neoforge {
/*import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent;
import net.neoforged.neoforge.common.NeoForge;
*///?}

//? if neoforge {
/*@Mod(Stargazer.MOD_ID)
*///?}
public final class Stargazer
//? if fabric {
        implements ModInitializer
//?}
{
    public static final String MOD_ID = "stargazer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //? if fabric {
    @Override
    public void onInitialize() {
        initCommon();
        GameRules.init();
        PlacedFeatures.init();
        EnergyHooks.registerEnergyStorages();
        FabricDefaultAttributeRegistry.register(
                EntityTypes.PLAYER,
                Player.createAttributes().add(StargazerAttributes.DASH_LEVEL, 0.0)
        );
    }
    //?}

    //? if neoforge {
    /*public Stargazer(IEventBus modEventBus, Dist dist) {
        modEventBus.addListener(this::commonSetup);

        RegistryKeys.ENTITY_DATA_SERIALIZERS.register(modEventBus);

        initCommon();
        GameRules.GAME_RULES.register();
        if (dist.isClient()) {
            StargazerClient.initClient();
            NeoForge.EVENT_BUS.register(StargazerNeoForgeRenderEvents.class);
        }
        modEventBus.register(StargazerNeoForgeAttributes.class);
        EnergyHooks.init(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }
    *///?}

    public static void initCommon() {
        EntityRegistry.init();
        StargazerNetworking.registerPackets();
        ModItems.init();
        SoundEffects.init();
        ModBlock.init();
        RegistryKeys.init();
        ComponentTypes.init();
        StarPattern.init();
        Patterns.init();
        ScreenHandlerTypes.init();
        CustomFeatures.init();
        StatusEffects.init();
        Potions.init();
        GameRules.init();
        DamageTypeRegistry.init();
        BiomeReg.init();
        BiomeTags.init();
        ConsumeEffectsRegistry.init();
        BlockTypes.init();
        PointOfIntrests.init();
        ItemGroup.init();
        CustomTags.init();
        Particles.init();
        TreesRegistry.init();
        PlacedFeatures.init();
        ModStats.init();
        init();
        Criterias.init();

        // Replaced Fabric ServerLifecycleEvents with Architectury LifecycleEvent
        LifecycleEvent.SERVER_STARTED.register(server -> {
            var registryManager = server.registryAccess();
            Registry<Patterns> patterns = registryManager.lookupOrThrow(RegistryKeys.STAR_PATTERN);
            LOGGER.info("Loaded: " + patterns.keySet().size() + " Star Patterns");
        });

        LifecycleEvent.SETUP.register(() -> {
            ModStats.setup();
        });

        // Replaced Fabric ServerTickEvents with Architectury TickEvent
        TickEvent.SERVER_LEVEL_POST.register(level -> {
            for (ServerPlayer player : level.players()) {
                PlayerCosmicGrav.tick(player);
                PlayerRedOrbGrav.tick(player);
            }
        });

        ModTraids.init();
        RecipeTypes.init();
    }
}
