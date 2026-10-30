package com.github.starcatcher21.stargazer.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Sniffer.class)
public interface SnifferAccessor {
    @Invoker("getHeadBlock")
    BlockPos invokeGetHeadBlock();
}
