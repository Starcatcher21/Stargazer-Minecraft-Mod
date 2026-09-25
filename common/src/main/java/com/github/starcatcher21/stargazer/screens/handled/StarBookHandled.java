package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.StarBookScreenHandler;
import com.github.starcatcher21.stargazer.screens.handled.buttons.StarPageButton;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
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
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        super.extractBackground(context, mouseX, mouseY, deltaTicks);

        Identifier pageTexture = page <= maxPage
                ? Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/book/page" + page + ".png")
                : Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/book/pagesoon.png");
        this.clearWidgets();
        StarPageButton buttonn = new StarPageButton(this.leftPos + 244, this.topPos + 157, true, _ -> page = page < maxPage + 1 ? page + 1 : maxPage + 1, true);
        StarPageButton button = new StarPageButton(this.leftPos + 7, this.topPos + 157, false, _ -> page = page > 0 ? page - 1 : 0, true);
        if (page != maxPage + 1) {
            this.addRenderableWidget((AbstractWidget) buttonn);
        }
        if (page != 0) {
            this.addRenderableWidget((AbstractWidget) button);
        }
        context.blit(RenderPipelines.GUI_TEXTURED, pageTexture, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 296, 296);
    }
}
