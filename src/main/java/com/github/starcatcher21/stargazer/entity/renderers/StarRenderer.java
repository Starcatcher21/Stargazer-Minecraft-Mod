package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.entity.models.StarModel;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.builtin.TextureLayerGeoLayer;

public class StarRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<Star, R> {
    public static Identifier BASE = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/base.png");
    public static Identifier MOON = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/moon.png");
    public static Identifier PONK = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/ponk.png");
    public static Identifier PACMAN = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/pacman.png");
    public static Identifier SUN = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/sun.png");
    public static Identifier FISH = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/fish.png");
    public static Identifier BRICK = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/brick.png");
    public static Identifier CREEPER = Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/creeper.png");
    public StarRenderer(EntityRendererFactory.Context context) {
        super(context, new StarModel());


        withRenderLayer(new TextureLayerGeoLayer<>(this,
                Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/base.png"),
                RenderLayers::armorCutoutNoCull){
            @Override
            protected Identifier getTextureResource(R renderState) {
                StarPatternsComponent spc = renderState.getGeckolibData(DataTickets.STAR_PATTERN);
                try {
                    for (StarPattern pattern : Patterns.patternList) {
                        if (spc.layers().getFirst().pattern().assetId().equals(pattern.assetId())) {
                            return Identifier.of(pattern.assetId().getNamespace(), "textures/entity/patterns/"+pattern.assetId().getPath()+".png");
                        }
                    }
                } catch (Exception ignored) {
                }
                return Identifier.of(Stargazer.MOD_ID, "textures/entity/patterns/base.png");
            }
        });
    }

    @Override
    public void addRenderData(Star animatable, @Nullable Void relatedObject, R renderState, float partialTick) {
        renderState.addGeckolibData(DataTickets.DYE_COLOR, animatable.getDyeColor());
        renderState.addGeckolibData(DataTickets.STAR_PATTERN, animatable.getSPC());
    }
}