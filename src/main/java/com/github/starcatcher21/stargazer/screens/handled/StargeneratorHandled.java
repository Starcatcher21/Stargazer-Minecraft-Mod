package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.StarGeneratorScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class StargeneratorHandled extends AbstractContainerScreen<StarGeneratorScreenHandler> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/stargenerator.png");
    private static final Identifier ARROW_TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/arrow_progress.png");

    public StargeneratorHandled(StarGeneratorScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        this.titleLabelX = 83;
        this.titleLabelY = 10;
        this.inventoryLabelY = 108;
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
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        int energyBarSize = Mth.ceil((this.menu.getEnergyPercent()) * 84);
        context.fill(i+10, j+13 + 84 - energyBarSize, i+19, j + 13 + 84, 0xAAFF0000);
        if (mouseX > i+10 && mouseX < i+19) {
            if (mouseY > j+13 && mouseY < j+13+84) {
                context.setTooltipForNextFrame(Component.nullToEmpty(""+this.menu.getEnergy()+"/"+this.menu.getMaxEnergy()), mouseX, mouseY);
            }
        }
        renderProgressArrow(context, i, j);
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if(menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 56, y + 31, 0, 0, menu.getScaledArrowProgress(), 7, 60, 7);
        }
    }
}