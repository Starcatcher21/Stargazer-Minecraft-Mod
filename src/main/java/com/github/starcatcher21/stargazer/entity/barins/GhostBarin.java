package com.github.starcatcher21.stargazer.entity.barins;

import com.github.starcatcher21.stargazer.entity.Ghost;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class GhostBarin {

    public static Brain<?> create(Brain<Ghost> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }
    private static final ImmutableList tasks = ImmutableList.of(new StayAboveWaterTask(0.8F), new FleeTask(2.5F), new UpdateLookControlTask(45, 90), new MoveToTargetTask());

    private static void addCoreActivities(Brain<Ghost> brain) {
        brain.setTaskList(Activity.CORE, 0, tasks);
    }
    private static void addIdleActivities(Brain<Ghost> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, WalkTowardsNearestVisibleWantedItemTask.create((allay) -> true, 1.75F, true, 32)), Pair.of(1, LookAtMobWithIntervalTask.follow(6.0F, UniformIntProvider.create(30, 60))), Pair.of(2, new RandomTask(ImmutableList.of(Pair.of(StrollTask.createSolidTargeting(1.0F), 2), Pair.of(GoToLookTargetTask.create(1.0F, 3), 2), Pair.of(new WaitTask(30, 60), 1))))), ImmutableSet.of());
    }

    public static void updateActivities(Ghost allay) {
        allay.getBrain().resetPossibleActivities(ImmutableList.of(Activity.IDLE));
    }
}
