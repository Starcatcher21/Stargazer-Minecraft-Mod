package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.classes.LodeStar;
import com.github.starcatcher21.stargazer.worldgen.dimensions.Dimensions;
import net.minecraft.block.Block;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.function.Function;

public final class ModItems {
    public static final Item STARDUST = register("stardust", Item::new, new Item.Settings());
    public static final Item PRISMATIC_SHARD = register("prismatic_shard", Item::new, new Item.Settings());
    public static final Item YELLOW_STAR = register("yellow_star", Item::new, new Item.Settings());
    public static final Item RED_STAR = register("red_star", Item::new, new Item.Settings());
    public static final Item BLUE_STAR = register("blue_star", Item::new, new Item.Settings());
    public static final Item PURPLE_STAR = register("purple_star", Item::new, new Item.Settings());
    public static final Item LODESTAR = register("lodestar", LodeStar::new, new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1));
    public static final Item GEODE_FRUIT = register("geode_fruit", Item::new, new Item.Settings());
    public static final Item GHOST_SPAWN_EGG = register("ghost_spawn_egg", (Item.Settings settings) -> new SpawnEggItem(EntityRegistry.GHOST_ENTITY, settings), new Item.Settings());
    public static final Item EYE_BAT_SPAWN_EGG = register("eye_bat_spawn_egg", (Item.Settings settings) -> new SpawnEggItem(EntityRegistry.EYE_BAT_ENTITY, settings), new Item.Settings());
    public static final Item AMETHYST_TURTLE_SPAWN_EGG = register("amethyst_turtle_spawn_egg", (Item.Settings settings) -> new SpawnEggItem(EntityRegistry.AMETHYST_TURTLE_ENTITY, settings), new Item.Settings());
    public static final Item GRAVICE = register("gravice", Item::new, new Item.Settings()
            .food(new FoodComponent(6, 6, true),
                    ConsumableComponent.builder()
                            .consumeEffect(new ConsumeEffect() {
                                @Override
                                public Type<? extends ConsumeEffect> getType() {
                                    return null;
                                }

                                @Override
                                public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
                                    if (!world.isClient && user instanceof ServerPlayerEntity serverPlayerEntity) {
                                        float f = MathHelper.clamp(user.getPitch(), -90.0F, 90.0F);
                                        double destX = user.getX();
                                        double destY = user.getY();
                                        double destZ = user.getZ();
                                        TeleportTarget target = new TeleportTarget(world.getServer().getWorld(Dimensions.REG_COSMIC_WORLD), new Vec3d(destX, destY, destZ), Vec3d.ZERO, user.getYaw(), f, TeleportTarget.NO_OP);
                                        user.teleportTo(target);
                                    }
                                    return true;
                                }
                            })
                            .build()
            )
    );

    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Stargazer.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static Function<Item.Settings, Item> createBlockItemWithUniqueName(Block block) {
        return settings -> new BlockItem(block, settings.useItemPrefixedTranslationKey());
    }

    public static void init() {}
}