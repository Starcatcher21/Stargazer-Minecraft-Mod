package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.StarBookScreenHandler;
import net.minecraft.client.gui.GuiGraphicsExtractor;
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
        super(handler, inventory, title, 296, 185);
        this.titleLabelX = -10000000;
        this.titleLabelY = -10000000;
        this.inventoryLabelX = -10000000;
        this.inventoryLabelY = -10000000;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new PageButton(this.leftPos + 244, this.topPos + 157, true, button -> page = page < maxPage + 1 ? page + 1 : maxPage + 1, true));
        this.addRenderableWidget(new PageButton(this.leftPos + 7, this.topPos + 157, false, button -> page = page > 0 ? page - 1 : 0, true));
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        super.extractBackground(context, mouseX, mouseY, deltaTicks);

        Identifier pageTexture = page <= maxPage
                ? Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/book/page" + page + ".png")
                : Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/book/pagesoon.png");
        context.blit(RenderPipelines.GUI_TEXTURED, pageTexture, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 296, 296);
    }
}
