package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import software.bernie.geckolib.constant.dataticket.DataTicket;

public class DataTickets {
    public static final DataTicket<DyeColor> DYE_COLOR = DataTicket.create("dyeColor", DyeColor.class);
    public static final DataTicket<StarPatternsComponent> STAR_PATTERN = DataTicket.create("starPatterns", StarPatternsComponent.class);
    public static final DataTicket<String> CUSTOM_NAME = DataTicket.create("customName", String.class);
    public static final DataTicket<ItemStack> INVENTORY = DataTicket.create("inventory", ItemStack.class);
    public static final DataTicket<Float> ROTATION = DataTicket.create("rotation", Float.class);
}
