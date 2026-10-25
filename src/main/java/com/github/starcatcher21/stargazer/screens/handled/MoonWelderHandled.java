package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.MoonWelderScreenHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
//? if >= 26.2 {
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.attribute.EnvironmentAttributes;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class MoonWelderHandled extends AbstractContainerScreen<MoonWelderScreenHandler> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder.png");
    public static final Identifier FULL           = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/full.png");
    public static final Identifier NEW            = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/new.png");
    public static final Identifier THIRD          = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/third.png");
    public static final Identifier FIRST          = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/first.png");
    public static final Identifier WAXING_CRESCENT= Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waxing_crescent.png");
    public static final Identifier WAXING_GIBBOUS = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waxing_gibbous.png");
    public static final Identifier WANING_CRESCENT= Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waning_crescent.png");
    public static final Identifier WANING_GIBBOUS = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waning_gibbous.png");
    public static final Identifier SUN            = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/sun.png");

    public MoonWelderHandled(MoonWelderScreenHandler handler, Inventory inventory, Component title) {
        //? if >= 26.2 {
        super(handler, inventory, title, 176, 200);
         //? } else {
        /*super(handler, inventory, title);
        this.imageHeight = 176;
        this.imageWidth = 200;
        *///? }
        this.titleLabelX = 103;
        this.titleLabelY = 10;
        this.inventoryLabelY = 85;
    }

    //? if >= 26.2 {
    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        super.extractBackground(context, mouseX, mouseY, deltaTicks);

        int i = this.leftPos;
        int j = this.topPos;
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0f, 0.0f,
                     this.imageWidth, this.imageHeight, 256, 256);

        Level world = Minecraft.getInstance().level;
        if (world != null) {
            Identifier phaseTexture = SUN;
            if (!world.isBrightOutside()) {
                EnvironmentAttributeSystem envAccess = world.environmentAttributes();
                int moonPhase = envAccess.getDimensionValue(EnvironmentAttributes.MOON_PHASE).index();
                phaseTexture = switch (moonPhase) {
                    case 0 -> FULL;
                    case 1 -> WANING_GIBBOUS;
                    case 2 -> THIRD;
                    case 3 -> WANING_CRESCENT;
                    case 4 -> NEW;
                    case 5 -> WAXING_CRESCENT;
                    case 6 -> FIRST;
                    case 7 -> WAXING_GIBBOUS;
                    default -> SUN;
                };
            }
            context.blit(RenderPipelines.GUI_TEXTURED, phaseTexture, i + 10, j + 10, 0, 0, 16, 16, 16, 16);
        }
    }
    //?} else {
    /*@Override
    protected void renderBg(GuiGraphics context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = this.topPos;

        // Background drawn in renderBg so the vanilla item tooltip
        // (rendered later in AbstractContainerScreen.render) stays on top.
        context.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        Level world = Minecraft.getInstance().level;
        if (world != null) {
            Identifier phaseTexture = SUN;
            if (world.isNight()) {
                int moonPhase = world.getMoonPhase();
                phaseTexture = switch (moonPhase) {
                    case 0 -> FULL;
                    case 1 -> WANING_GIBBOUS;
                    case 2 -> THIRD;
                    case 3 -> WANING_CRESCENT;
                    case 4 -> NEW;
                    case 5 -> WAXING_CRESCENT;
                    case 6 -> FIRST;
                    case 7 -> WAXING_GIBBOUS;
                    default -> SUN;
                };
            }
            context.blit(phaseTexture, i + 10, j + 10, 0, 0, 16, 16, 16, 16);
        }
    }

    @Override
    public void render(GuiGraphics arg, int l, int m, float g) {
        super.render(arg, l, m, g);
        super.renderTooltip(arg, l, m);
    }
    *///?}
}
