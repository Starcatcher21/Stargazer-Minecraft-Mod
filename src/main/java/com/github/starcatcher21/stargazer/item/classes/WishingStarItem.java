package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class WishingStarItem extends BoatItem {
    private final EntityType<? extends Star> entityType;
    private final DyeColor defaultColor;

    public WishingStarItem(EntityType<? extends Star> type, DyeColor defaultColor, Item.Properties settings) {
        super(type, settings.stacksTo(1));
        this.entityType = type;
        this.defaultColor = defaultColor;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(world, user, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else {
            Vec3 vec3d = user.getViewVector(1.0F);
            double d = 5.0;
            List<Entity> list = world.getEntities(user, user.getBoundingBox().expandTowards(vec3d.scale(5.0)).inflate(1.0), EntitySelector.CAN_BE_PICKED);
            if (!list.isEmpty()) {
                Vec3 vec3d2 = user.getEyePosition();

                for (Entity entity : list) {
                    AABB box = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (box.contains(vec3d2)) {
                        return InteractionResult.PASS;
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                AbstractBoat abstractBoatEntity = this.getBoat(world, hitResult, itemStack, user);
                if (abstractBoatEntity == null) {
                    return InteractionResult.FAIL;
                } else {
                    abstractBoatEntity.setYRot(user.getYRot());
                    if (!world.noCollision(abstractBoatEntity, abstractBoatEntity.getBoundingBox())) {
                        return InteractionResult.FAIL;
                    } else {
                        if (!world.isClientSide()) {
                            world.addFreshEntity(abstractBoatEntity);
                            world.gameEvent(user, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                            itemStack.consume(1, user);
                        }

                        user.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResult.SUCCESS;
                    }
                }
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    private Star getBoat(Level world, HitResult hitResult, ItemStack stack, Player player) {
        Star abstractBoatEntity = entityType.create(world, EntitySpawnReason.SPAWN_ITEM_USE);
        var patterns = stack.get(ComponentTypes.STAR_PATTERNS);
        if (abstractBoatEntity != null) {
            try {
                abstractBoatEntity.setSPC(Optional.of(patterns));
            } catch (Exception ignored) {}
            abstractBoatEntity.setDyeColor(defaultColor);
            Vec3 vec3d = hitResult.getLocation();
            abstractBoatEntity.setInitialPos(vec3d.x, vec3d.y, vec3d.z);
            if (world instanceof ServerLevel serverWorld) {
                EntityType.createDefaultStackConfig(serverWorld, stack, player).apply(abstractBoatEntity);
            }
        }

        return abstractBoatEntity;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        var patterns = stack.get(ComponentTypes.STAR_PATTERNS);
        try {
            if (!patterns.layers().getFirst().pattern().assetId().equals(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "base"))) {
                textConsumer.accept(patterns.getTooltip());
            }
        } catch (Exception ignored) {
        }
    }
}