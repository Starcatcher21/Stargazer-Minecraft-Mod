package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.StarCrusherScreenHandler;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import java.util.List;
//? if >= 26.2 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}

public class StarCrusherHandled extends AbstractContainerScreen<StarCrusherScreenHandler> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/starcrusher.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/arrow_progress_down2.png");

    public StarCrusherHandled(StarCrusherScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title /*? if >= 26.2 { */ , 176, 200 /*? } */);
        //? if < 26.2 {
        /*this.imageHeight = 176;
        this.imageWidth = 200;
        *///? }
        this.titleLabelX = 83;
        this.titleLabelY = 10;
        this.inventoryLabelY = 108;
    }

    //? if >= 26.2 {
    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        super.extractBackground(context, mouseX, mouseY, deltaTicks);

        int i = this.leftPos;
        int j = this.topPos;
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j,
                0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        int energyBarSize = Mth.ceil(this.menu.getEnergyPercent() * 84);
        context.fill(i + 10, j + 13 + 84 - energyBarSize,
                     i + 19, j + 13 + 84,
                     0xAAFF0000);

        if (mouseX > i + 10 && mouseX < i + 19 && mouseY > j + 13 && mouseY < j + 13 + 84) {
            context.setTooltipForNextFrame(
                    Component.nullToEmpty(this.menu.getEnergy() + "/" + this.menu.getMaxEnergy()),
                    mouseX, mouseY);
        }
        renderProgressArrow26(context, i, j);
    }

    private void renderProgressArrow26(GuiGraphicsExtractor context, int x, int y) {
        if (menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE,
                    x + 53, y + 31,
                    0, 0,
                    6, menu.getScaledArrowProgress(),
                    6, 56);
        }
    }
    //?} else {
    /*@Override
    public void renderBg(GuiGraphics context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = this.topPos;
        context.blit(TEXTURE, i, j,
                0, 0,
                this.imageWidth, this.imageHeight,
                256, 256);

        int energyBarSize = Mth.ceil(this.menu.getEnergyPercent() * 84);
        context.fill(i + 10, j + 13 + 84 - energyBarSize,
                i + 19, j + 13 + 84,
                0xAAFF0000);

        renderProgressArrow(context, i, j);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        int i = this.leftPos;
        int j = this.topPos;
        if (mouseX > i + 10 && mouseX < i + 19 && mouseY > j + 13 && mouseY < j + 13 + 84) {
            this.setTooltipForNextRenderPass(Component.nullToEmpty(this.menu.getEnergy() + "/" + this.menu.getMaxEnergy()));
        }
        this.renderTooltip(context, mouseX, mouseY);
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if (menu.isCrafting()) {
            context.blit(ARROW_TEXTURE,
                    x + 53, y + 31,
                    0, 0,
                    6, menu.getScaledArrowProgress(),
                    6, 56);
        }
    }
    *///?}
}
