package com.github.starcatcher21.stargazer.screens.handled;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.MoonWelderScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.WorldEnvironmentAttributeAccess;

public class MoonWelderHandled extends HandledScreen<MoonWelderScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder.png");
    public static final Identifier FULL = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/full.png");
    public static final Identifier NEW = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/new.png");
    public static final Identifier THIRD = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/third.png");
    public static final Identifier FIRST = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/first.png");
    public static final Identifier WAXING_CRESCENT = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waxing_crescent.png");
    public static final Identifier WAXING_GIBBOUS = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waxing_gibbous.png");
    public static final Identifier WANING_CRESCENT = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waning_crescent.png");
    public static final Identifier WANING_GIBBOUS = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/waning_gibbous.png");
    public static final Identifier SUN = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon/sun.png");

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
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0f, 0.0f, this.backgroundWidth, this.backgroundHeight , 256, 256);
        World world = MinecraftClient.getInstance().world;
        WorldEnvironmentAttributeAccess envAccess = world.getEnvironmentAttributes();
        int moonPhase = envAccess.getAttributeValue(EnvironmentAttributes.MOON_PHASE_VISUAL).getIndex();
        if (world.isDay()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, SUN, i + 10, j + 10, 0, 0, 16, 16, 16, 16);
        } else {
            switch (moonPhase) {
                case 0: context.drawTexture(RenderPipelines.GUI_TEXTURED, FULL, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 1: context.drawTexture(RenderPipelines.GUI_TEXTURED, WANING_GIBBOUS, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 2: context.drawTexture(RenderPipelines.GUI_TEXTURED, THIRD, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 3: context.drawTexture(RenderPipelines.GUI_TEXTURED, WANING_CRESCENT, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 4: context.drawTexture(RenderPipelines.GUI_TEXTURED, NEW, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 5: context.drawTexture(RenderPipelines.GUI_TEXTURED, WAXING_CRESCENT, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 6: context.drawTexture(RenderPipelines.GUI_TEXTURED, FIRST, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                case 7: context.drawTexture(RenderPipelines.GUI_TEXTURED, WAXING_GIBBOUS, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
                default: context.drawTexture(RenderPipelines.GUI_TEXTURED, SUN, i + 10, j + 10, 0, 0, 16, 16, 16, 16); break;
            }
        }
    }
}