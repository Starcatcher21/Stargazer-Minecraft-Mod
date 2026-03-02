package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.Identifier;

public class Criterias {
    public static final Starcatching starcatching = Criteria.register(Identifier.of(Stargazer.MOD_ID, "starcatching").toString(), new Starcatching());

    public static void init() {
    }
}
