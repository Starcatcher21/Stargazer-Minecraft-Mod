package com.github.starcatcher21.stargazer.entity.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.EnumSet;

public class FlyAroundGoal extends Goal {
    private final FlyingEntity mob;
    private final double speed;

    public FlyAroundGoal(FlyingEntity mob, double speed) {
         this.mob = mob;
         this.speed = speed;
         this.setControls(EnumSet.of(Control.MOVE)); // Indicate this goal controls movement
    }

    @Override
    public boolean canStart() {
        return this.mob.getRandom().nextInt(5) > 2; // Simple example: 10% chance to start
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.getNavigation().isIdle(); // Check if pathfinding is still active
    }

    @Override
    public void start() {
        Vec3d randomPos = this.getRandomFlightTarget();
        double targetX = randomPos.x;
        double targetY = randomPos.y;
        double targetZ = randomPos.z;
        this.mob.setPositionTarget(new BlockPos( (int) targetX, (int) targetY, (int) targetZ), 7);
        this.mob.getNavigation().startMovingTo(targetX, targetY, targetZ, this.speed);
    }

    @Override
    public void tick() {
    }

    private Vec3d getRandomFlightTarget() {
        Random random = this.mob.getRandom();
        double x = this.mob.getX() + (random.nextDouble() - 0.5) * 16.0;
        double y = this.mob.getY() + (random.nextDouble() - 0.5) * 8.0; // Fly higher/lower
        double z = this.mob.getZ() + (random.nextDouble() - 0.5) * 16.0;
        return new Vec3d(x, y, z);
    }
}
