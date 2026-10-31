//? if < 26.2 {
package com.github.starcatcher21.stargazer.helpers;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Shapes {
    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{ shape, net.minecraft.world.phys.shapes.Shapes.empty() };

        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
                buffer[1] = net.minecraft.world.phys.shapes.Shapes.or(buffer[1], net.minecraft.world.phys.shapes.Shapes.box(
                        1 - maxZ, minY, minX,
                        1 - minZ, maxY, maxX
                ));
            });
            buffer[0] = buffer[1];
            buffer[1] = net.minecraft.world.phys.shapes.Shapes.empty();
        }
        return buffer[0];
    }
}
//? }
