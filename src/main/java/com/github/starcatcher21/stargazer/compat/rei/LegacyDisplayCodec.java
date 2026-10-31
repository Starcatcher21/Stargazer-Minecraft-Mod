package com.github.starcatcher21.stargazer.compat.rei;

import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.Level;

final class LegacyDisplayCodec {
    private LegacyDisplayCodec() {}

    static <T> Tag encode(Codec<T> codec, T value) {
        return codec.encodeStart(registryOps(), value).getOrThrow();
    }

    static <T> T decode(Codec<T> codec, Tag tag) {
        return codec.parse(registryOps(), tag).getOrThrow();
    }

    private static RegistryOps<Tag> registryOps() {
        Level level = Minecraft.getInstance().level;
        if (level == null) {
            throw new IllegalStateException("Cannot serialize REI displays without a client world");
        }
        return RegistryOps.create(NbtOps.INSTANCE, level.registryAccess());
    }
}
