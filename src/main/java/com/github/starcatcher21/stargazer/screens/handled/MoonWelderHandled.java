package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.MoonWelderScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MoonWelderHandled extends HandledScreen<MoonWelderScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder.png");
    private static final Identifier FULL = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/full.png");
    private static final Identifier NEW = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/new.png");
    private static final Identifier THIRD = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/third.png");
    private static final Identifier FIRST = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/first.png");
    private static final Identifier WAXING_CRESCENT = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waxing_crescent.png");
    private static final Identifier WAXING_GIBBOUS = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waxing_gibbous.png");
    private static final Identifier WANING_CRESCENT = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waning_crescent.png");
    private static final Identifier WANING_GIBBOUS = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waning_gibbous.png");
    private static final Identifier SUN = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/sun.png");

    public MoonWelderHandled(MoonWelderScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.titleX = 103;
        this.titleY = 10;
        this.playerInventoryTitleY = 85;
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
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE, i, j, 0.0f, 0.0f, this.backgroundWidth, this.backgroundHeight , 256, 256);
        World world = MinecraftClient.getInstance().world;
        int moonPhase = world.getMoonPhase();
        if (world.isDay()) {
            context.drawTexture(RenderLayer::getGuiTextured, SUN, i + 10, j + 10, 0, 0, 16, 16, 16, 16);
        } else {
            switch (moonPhase) {
                case 0: context.drawTexture(RenderLayer::getGuiTextured, FULL, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 1: context.drawTexture(RenderLayer::getGuiTextured, WANING_GIBBOUS, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 2: context.drawTexture(RenderLayer::getGuiTextured, THIRD, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 3: context.drawTexture(RenderLayer::getGuiTextured, WANING_CRESCENT, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 4: context.drawTexture(RenderLayer::getGuiTextured, NEW, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 5: context.drawTexture(RenderLayer::getGuiTextured, WAXING_CRESCENT, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 6: context.drawTexture(RenderLayer::getGuiTextured, FIRST, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 7: context.drawTexture(RenderLayer::getGuiTextured, WAXING_GIBBOUS, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                default: context.drawTexture(RenderLayer::getGuiTextured, SUN, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
            }
        }
    }
}