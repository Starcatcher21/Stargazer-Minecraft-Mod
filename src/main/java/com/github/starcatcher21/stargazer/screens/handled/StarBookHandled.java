package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.StarBookScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class StarBookHandled extends AbstractContainerScreen<StarBookScreenHandler> {
    private int maxPage = 11;
    private int page = 0;

    public StarBookHandled(StarBookScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        this.titleLabelX = -10000000;
        this.titleLabelY = -10000000;
        this.inventoryLabelX = -10000000;
        this.inventoryLabelY = -10000000;
        this.imageWidth = 296;
        this.imageHeight = 185;
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
        this.addRenderableWidget(new PageButton(i + 244, j + 157, true, button -> page = page != maxPage + 1 ? page+1 : maxPage + 1, true));
        this.addRenderableWidget(new PageButton(i + 7, j + 157, false, button -> page = page != 0 ? page-1 : 0, true));
        if (page <= maxPage) {
            context.blit(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/book/page" + page + ".png"), i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 296, 296);
        } else {
            context.blit(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/book/pagesoon.png"), i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 296, 296);
        }
    }
}