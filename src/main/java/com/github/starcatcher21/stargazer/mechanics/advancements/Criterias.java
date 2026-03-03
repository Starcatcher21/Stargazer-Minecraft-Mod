package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.Identifier;

public class Criterias {
    public static final Starcatching starcatching = Criteria.register(Identifier.of(Stargazer.MOD_ID, "starcatching").toString(), new Starcatching());
    public static final CosmicPortal cosmicPortal = Criteria.register(Identifier.of(Stargazer.MOD_ID, "cosmic_portal").toString(), new CosmicPortal());
    public static final StarTrap starTrap = Criteria.register(Identifier.of(Stargazer.MOD_ID, "star_trap").toString(), new StarTrap());

    public static void init() {
    }
}
