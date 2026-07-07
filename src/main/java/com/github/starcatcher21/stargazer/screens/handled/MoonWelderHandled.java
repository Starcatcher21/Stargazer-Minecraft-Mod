package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.MoonWelderScreenHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.MoonPhase;

public class MoonWelderHandled extends AbstractContainerScreen<MoonWelderScreenHandler> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder.png");
    public static final Identifier FULL = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/full.png");
    public static final Identifier NEW = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/new.png");
    public static final Identifier THIRD = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/third.png");
    public static final Identifier FIRST = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/first.png");
    public static final Identifier WAXING_CRESCENT = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waxing_crescent.png");
    public static final Identifier WAXING_GIBBOUS = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waxing_gibbous.png");
    public static final Identifier WANING_CRESCENT = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waning_crescent.png");
    public static final Identifier WANING_GIBBOUS = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/waning_gibbous.png");
    public static final Identifier SUN = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon/sun.png");

    public MoonWelderHandled(MoonWelderScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        this.titleLabelX = 103;
        this.titleLabelY = 10;
        this.inventoryLabelY = 85;
        this.imageHeight = 200;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
        this.renderTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0f, 0.0f, this.imageWidth, this.imageHeight , 256, 256);
        Level world = Minecraft.getInstance().level;
        EnvironmentAttributeSystem envAccess = world.environmentAttributes();
        int moonPhase = envAccess.getDimensionValue(EnvironmentAttributes.MOON_PHASE).index();
        if (world.isBrightOutside()) {
            context.blit(RenderPipelines.GUI_TEXTURED, SUN, i + 10, j + 10, 0, 0, 16, 16, 16, 16);
        } else {
            switch (moonPhase) {
                case 0: context.blit(RenderPipelines.GUI_TEXTURED, FULL, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 1: context.blit(RenderPipelines.GUI_TEXTURED, WANING_GIBBOUS, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 2: context.blit(RenderPipelines.GUI_TEXTURED, THIRD, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 3: context.blit(RenderPipelines.GUI_TEXTURED, WANING_CRESCENT, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 4: context.blit(RenderPipelines.GUI_TEXTURED, NEW, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 5: context.blit(RenderPipelines.GUI_TEXTURED, WAXING_CRESCENT, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 6: context.blit(RenderPipelines.GUI_TEXTURED, FIRST, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 7: context.blit(RenderPipelines.GUI_TEXTURED, WAXING_GIBBOUS, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                default: context.blit(RenderPipelines.GUI_TEXTURED, SUN, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
            }
        }
    }
}