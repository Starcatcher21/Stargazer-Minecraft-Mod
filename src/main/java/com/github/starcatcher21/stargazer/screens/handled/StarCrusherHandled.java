package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.NightWatcherScreenHandler;
import com.github.starcatcher21.stargazer.screens.StarCrusherScreenHandler;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class StarCrusherHandled extends HandledScreen<StarCrusherScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/starcrusher.png");
    private static final Identifier ARROW_TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/arrow_progress_down2.png");

    public StarCrusherHandled(StarCrusherScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.titleX = 83;
        this.titleY = 10;
        this.playerInventoryTitleY = 108;
        this.backgroundHeight = 200;
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
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
        int energyBarSize = MathHelper.ceil((this.handler.getEnergyPercent()) * 84);
        context.fill(i+10, j+13 + 84 - energyBarSize, i+19, j + 13 + 84, 0xAAFF0000);
        if (mouseX > i+10 && mouseX < i+19) {
            if (mouseY > j+13 && mouseY < j+13+84) {
                context.drawTooltip(Text.of(""+this.handler.getEnergy()+"/"+this.handler.getMaxEnergy()), mouseX, mouseY);
            }
        }
        renderProgressArrow(context, i, j);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 53, y + 31, 0, 0, 6, handler.getScaledArrowProgress(), 6, 56);
        }
    }
}