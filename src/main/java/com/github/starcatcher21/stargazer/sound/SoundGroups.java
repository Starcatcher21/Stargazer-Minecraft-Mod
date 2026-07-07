package com.github.starcatcher21.stargazer.sound;

import java.util.Random;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class SoundGroups {
    private static final Random random = new Random();
    public static final SoundType STAR = new SoundType(
            0.1F,
            1.5F + random.nextFloat(0.2F),
            SoundEffects.BLOCK_COSMIC_BREAK,
            SoundEvents.EMPTY,
            SoundEffects.BLOCK_COSMIC_PLACE,
            SoundEvents.EMPTY,
            SoundEffects.BLOCK_COSMIC_LAND
    );
}
