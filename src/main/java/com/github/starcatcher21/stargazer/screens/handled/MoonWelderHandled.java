package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.MoonWelderScreenHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
//? if >= 26.2 {
/*import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.attribute.EnvironmentAttributes;
*///?} else {
import net.minecraft.client.gui.GuiGraphics;
//?}
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class MoonWelderHandled extends AbstractContainerScreen<MoonWelderScreenHandler> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder.png");
    public static final ResourceLocation FULL           = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/full.png");
    public static final ResourceLocation NEW            = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/new.png");
    public static final ResourceLocation THIRD          = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/third.png");
    public static final ResourceLocation FIRST          = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/first.png");
    public static final ResourceLocation WAXING_CRESCENT= ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waxing_crescent.png");
    public static final ResourceLocation WAXING_GIBBOUS = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waxing_gibbous.png");
    public static final ResourceLocation WANING_CRESCENT= ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waning_crescent.png");
    public static final ResourceLocation WANING_GIBBOUS = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waning_gibbous.png");
    public static final ResourceLocation SUN            = ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/sun.png");

    public MoonWelderHandled(MoonWelderScreenHandler handler, Inventory inventory, Component title) {
        //? if >= 26.2 {
        /*super(handler, inventory, title, 176, 200);
        *///? } else {
        super(handler, inventory, title);
        //? }
        this.titleLabelX = 103;
        this.titleLabelY = 10;
        this.inventoryLabelY = 85;
    }

    /* ---------------- 26.2+ ------------------------------------------- */
    //? if >= 26.2 {
    /*@Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        super.extractBackground(context, mouseX, mouseY, deltaTicks);

        int i = this.leftPos;
        int j = this.topPos;
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0f, 0.0f,
                     this.imageWidth, this.imageHeight, 256, 256);

        Level world = Minecraft.getInstance().level;
        if (world != null) {
            ResourceLocation phaseTexture = SUN;
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
    *///?} else {
    /* ---------------- 1.21.1 ----------------------------------------- */
    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        int i = this.leftPos;
        int j = this.topPos;

        // 1.21.1 blit: (texture, x, y, u, v, w, h, texW, texH)
        context.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        Level world = Minecraft.getInstance().level;
        if (world != null) {
            ResourceLocation phaseTexture = SUN;
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
    protected void renderBg(GuiGraphics arg, float f, int i, int j) {

    }
    //?}
}
