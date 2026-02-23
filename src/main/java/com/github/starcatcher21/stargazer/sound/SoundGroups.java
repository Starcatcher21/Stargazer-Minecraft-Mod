package com.github.starcatcher21.stargazer.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

public class SoundGroups {
    private static final Random random = new Random();
    public static final BlockSoundGroup STAR = new BlockSoundGroup(
            0.1F,
            1.5F + random.nextFloat(0.2F),
            SoundEffects.BLOCK_COSMIC_BREAK,
            SoundEvents.INTENTIONALLY_EMPTY,
            SoundEffects.BLOCK_COSMIC_PLACE,
            SoundEvents.INTENTIONALLY_EMPTY,
            SoundEffects.BLOCK_COSMIC_LAND
    );
}
