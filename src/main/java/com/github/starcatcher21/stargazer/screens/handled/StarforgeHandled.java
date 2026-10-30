package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.StarforgeScreenHandler;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
//? if >= 26.2 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}

public class StarforgeHandled extends AbstractContainerScreen<StarforgeScreenHandler> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/starforge/starforge.png");

    public StarforgeHandled(StarforgeScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title /*? if >= 26.2 { */, 176, 200/*? } */);
        //? if < 26.2 {
        /*this.imageHeight = 200;
        this.imageWidth = 176;
        *///? }
        this.titleLabelX = 110;
        this.titleLabelY = 10;
        this.inventoryLabelY = 108;
    }

    /* ---------------- 26.2+ ------------------------------------------- */
    //? if >= 26.2 {
    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        super.extractBackground(context, mouseX, mouseY, deltaTicks);
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
                this.leftPos, this.topPos,
                0.0F, 0.0F,
                this.imageWidth, this.imageHeight,
                256, 256);
    }
    //?} else {
    /*/^ ---------------- 1.21.1 ----------------------------------------- ^/
    @Override
    public void renderBg(GuiGraphics context, float deltaTicks, int mouseX, int mouseY) {
        context.blit(TEXTURE,
                this.leftPos, this.topPos,
                0, 0,
                this.imageWidth, this.imageHeight,
                256, 256);
    }

    @Override
    public void render(GuiGraphics arg, int l, int m, float g) {
        super.render(arg, l, m, g);
        this.renderTooltip(arg, l, m);
    }
    *///?}
}
