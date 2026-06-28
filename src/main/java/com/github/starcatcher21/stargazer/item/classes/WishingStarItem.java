package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class WishingStarItem extends BoatItem {
    private final EntityType<? extends Star> entityType;
    private final DyeColor defaultColor;

    public WishingStarItem(EntityType<? extends Star> type, DyeColor defaultColor, Item.Settings settings) {
        super(type, settings.maxCount(1));
        this.entityType = type;
        this.defaultColor = defaultColor;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return ActionResult.PASS;
        } else {
            Vec3d vec3d = user.getRotationVec(1.0F);
            double d = 5.0;
            List<Entity> list = world.getOtherEntities(user, user.getBoundingBox().stretch(vec3d.multiply(5.0)).expand(1.0), EntityPredicates.CAN_HIT);
            if (!list.isEmpty()) {
                Vec3d vec3d2 = user.getEyePos();

                for (Entity entity : list) {
                    Box box = entity.getBoundingBox().expand(entity.getTargetingMargin());
                    if (box.contains(vec3d2)) {
                        return ActionResult.PASS;
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                AbstractBoatEntity abstractBoatEntity = this.createEntity(world, hitResult, itemStack, user);
                if (abstractBoatEntity == null) {
                    return ActionResult.FAIL;
                } else {
                    abstractBoatEntity.setYaw(user.getYaw());
                    if (!world.isSpaceEmpty(abstractBoatEntity, abstractBoatEntity.getBoundingBox())) {
                        return ActionResult.FAIL;
                    } else {
                        if (!world.isClient()) {
                            world.spawnEntity(abstractBoatEntity);
                            world.emitGameEvent(user, GameEvent.ENTITY_PLACE, hitResult.getPos());
                            itemStack.decrementUnlessCreative(1, user);
                        }

                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        return ActionResult.SUCCESS;
                    }
                }
            } else {
                return ActionResult.PASS;
            }
        }
    }

    private Star createEntity(World world, HitResult hitResult, ItemStack stack, PlayerEntity player) {
        Star abstractBoatEntity = entityType.create(world, SpawnReason.SPAWN_ITEM_USE);
        var patterns = stack.get(ComponentTypes.STAR_PATTERNS);
        if (abstractBoatEntity != null) {
            try {
                abstractBoatEntity.setSPC(Optional.of(patterns));
            } catch (Exception ignored) {}
            abstractBoatEntity.setDyeColor(defaultColor);
            Vec3d vec3d = hitResult.getPos();
            abstractBoatEntity.initPosition(vec3d.x, vec3d.y, vec3d.z);
            if (world instanceof ServerWorld serverWorld) {
                EntityType.copier(serverWorld, stack, player).accept(abstractBoatEntity);
            }
        }

        return abstractBoatEntity;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        var patterns = stack.get(ComponentTypes.STAR_PATTERNS);
        try {
            if (!patterns.layers().getFirst().pattern().assetId().equals(Identifier.of(Stargazer.MOD_ID, "base"))) {
                textConsumer.accept(patterns.getTooltip());
            }
        } catch (Exception ignored) {
        }
    }
}