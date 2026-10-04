package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.nbt.ComponentTypes;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
//? if >= 26.2 {
/*import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
*///?} else {
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.InteractionResultHolder;
//? }
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class WishingStarItem extends BoatItem {
    private final EntityType<? extends Star> entityType;
    private final DyeColor defaultColor;

    public WishingStarItem(EntityType<? extends Star> type, DyeColor defaultColor, Properties settings) {
        //? if >= 26.2 {
        /*super(type, settings.stacksTo(1));
        *///? } else {
        super(true, Boat.Type.BAMBOO, settings.stacksTo(1));
        //? }
        this.entityType = type;
        this.defaultColor = defaultColor;
    }

    @Override
    //? if >= 26.2 {
    /*public InteractionResult use(Level world, Player user, InteractionHand hand) {
    *///? } else {
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
    //? }
        ItemStack itemStack = user.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(world, user, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            //? if >= 26.2 {
            /*return InteractionResult.PASS;
            *///? } else {
            return InteractionResultHolder.pass(itemStack);
            //? }
        } else {
            Vec3 vec3d = user.getViewVector(1.0F);
            double d = 5.0;
            //? if >= 26.2 {
            /*List<Entity> list = world.getEntities(user, user.getBoundingBox().expandTowards(vec3d.scale(5.0)).inflate(1.0), EntitySelector.CAN_BE_PICKED);
            *///? } else {
            List<Entity> list = world.getEntities(user, user.getBoundingBox().expandTowards(vec3d.scale(5.0)).inflate(1.0), EntitySelector.CAN_BE_COLLIDED_WITH);
            //? }
            if (!list.isEmpty()) {
                Vec3 vec3d2 = user.getEyePosition();

                for (Entity entity : list) {
                    AABB box = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (box.contains(vec3d2)) {
                        //? if >= 26.2 {
                        /*return InteractionResult.PASS;
                         *///? } else {
                        return InteractionResultHolder.pass(itemStack);
                        //? }
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                //? if >= 26.2 {
                /*AbstractBoat abstractBoatEntity = this.getBoat(world, hitResult, itemStack, user);
                *///? } else {
                Boat abstractBoatEntity = this.getBoat(world, hitResult, itemStack, user);
                //? }
                if (abstractBoatEntity == null) {
                    //? if >= 26.2 {
                    /*return InteractionResult.FAIL;
                     *///? } else {
                    return InteractionResultHolder.fail(itemStack);
                    //? }
                } else {
                    abstractBoatEntity.setYRot(user.getYRot());
                    if (!world.noCollision(abstractBoatEntity, abstractBoatEntity.getBoundingBox())) {
                        //? if >= 26.2 {
                        /*return InteractionResult.FAIL;
                         *///? } else {
                        return InteractionResultHolder.fail(itemStack);
                        //? }
                    } else {
                        if (!world.isClientSide()) {
                            world.addFreshEntity(abstractBoatEntity);
                            world.gameEvent(user, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                            itemStack.consume(1, user);
                        }

                        user.awardStat(Stats.ITEM_USED.get(this));
                        //? if >= 26.2 {
                        /*return InteractionResult.SUCCESS;
                         *///? } else {
                        return InteractionResultHolder.success(itemStack);
                        //? }
                    }
                }
            } else {
                //? if >= 26.2 {
                /*return InteractionResult.PASS;
                 *///? } else {
                return InteractionResultHolder.pass(itemStack);
                //? }
            }
        }
    }


    private Star getBoat(Level world, HitResult hitResult, ItemStack stack, Player player) {
        Star abstractBoatEntity = entityType.create(world
                //? if >= 26.2 {
                /*, EntitySpawnReason.SPAWN_ITEM_USE
                *///? }
        );
        var patterns = stack.get(ComponentTypes.STAR_PATTERNS.get());
        if (abstractBoatEntity != null) {
            try {
                abstractBoatEntity.setSPC(Optional.of(patterns));
            } catch (Exception ignored) {}
            abstractBoatEntity.setDyeColor(defaultColor);
            Vec3 vec3d = hitResult.getLocation();
            //? if >= 26.2 {
            /*abstractBoatEntity.setInitialPos(vec3d.x, vec3d.y, vec3d.z);
            *///? }
            if (world instanceof ServerLevel serverWorld) {
                EntityType.createDefaultStackConfig(serverWorld, stack, player)
                        //? if >= 26.2 {
                        /*.apply(abstractBoatEntity)
                        *///? } else {
                        .accept(abstractBoatEntity);
                        //? }
                ;
            }
        }

        return abstractBoatEntity;
    }



    //? if >= 26.2 {
    /*@Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        var patterns = stack.get(ComponentTypes.STAR_PATTERNS.get());
        try {
            if (!patterns.layers().getFirst().pattern().assetId().equals(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "base"))) {
                textConsumer.accept(patterns.getTooltip());
            }
        } catch (Exception ignored) {
        }
    }
    *///? } else {
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> textConsumer, TooltipFlag tooltipFlag) {
        var patterns = stack.get(ComponentTypes.STAR_PATTERNS.get());
        try {
            if (!patterns.layers().getFirst().pattern().assetId().equals(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "base"))) {
                textConsumer.add(patterns.getTooltip());
            }
        } catch (Exception ignored) {
        }
    }
    //? }
}
