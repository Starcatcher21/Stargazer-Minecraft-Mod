package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.NightWatcherScreenHandler;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class NightWatcherHandled extends AbstractContainerScreen<NightWatcherScreenHandler> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/nightwatcher.png");
    private static final Identifier ARROW_TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/arrow_progress_down.png");

    public NightWatcherHandled(NightWatcherScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, 176, 200);
        this.titleLabelX = 83;
        this.titleLabelY = 10;
        this.inventoryLabelY = 108;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        super.extractBackground(context, mouseX, mouseY, deltaTicks);

        int i = this.leftPos;
        int j = this.topPos;
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        int energyBarSize = Mth.ceil(this.menu.getEnergyPercent() * 84);
        context.fill(i + 10, j + 13 + 84 - energyBarSize, i + 19, j + 13 + 84, 0xAAFF0000);
        if (mouseX > i + 10 && mouseX < i + 19 && mouseY > j + 13 && mouseY < j + 13 + 84) {
            context.setTooltipForNextFrame(Component.nullToEmpty(this.menu.getEnergy() + "/" + this.menu.getMaxEnergy()), mouseX, mouseY);
        }
        renderProgressArrow(context, i, j);
    }

    private void renderProgressArrow(GuiGraphicsExtractor context, int x, int y) {
        if (menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 53, y + 31, 0, 0, 6, menu.getScaledArrowProgress(), 6, 56);
        }
    }
}
