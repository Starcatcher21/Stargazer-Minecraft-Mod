package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.StarBookScreenHandler;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.PageTurnWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class StarBookHandled extends HandledScreen<StarBookScreenHandler> {
    private int maxPage = 10;
    private int page = 0;

    public StarBookHandled(StarBookScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.titleX = -10000000;
        this.titleY = -10000000;
        this.playerInventoryTitleX = -10000000;
        this.playerInventoryTitleY = -10000000;
        this.backgroundWidth = 296;
        this.backgroundHeight = 185;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;
        this.addDrawableChild(new PageTurnWidget(i + 244, j + 157, true, button -> page = page != maxPage + 1 ? page+1 : maxPage + 1, true));
        this.addDrawableChild(new PageTurnWidget(i + 7, j + 157, false, button -> page = page != 0 ? page-1 : 0, true));
        if (page <= maxPage) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, Identifier.of(Stargazer.MOD_ID, "textures/gui/book/page" + page + ".png"), i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 296, 296);
        } else {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, Identifier.of(Stargazer.MOD_ID, "textures/gui/book/pagesoon.png"), i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 296, 296);
        }
    }
}