package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.entity.models.StarModel;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.builtin.TextureLayerGeoLayer;

public class StarRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<Star, R> {
    public static Identifier BASE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/base.png");
    public static Identifier MOON = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/moon.png");
    public static Identifier PONK = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/ponk.png");
    public static Identifier PACMAN = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/pacman.png");
    public static Identifier SUN = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/sun.png");
    public static Identifier FISH = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/fish.png");
    public static Identifier BRICK = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/brick.png");
    public static Identifier CREEPER = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/creeper.png");
    public StarRenderer(EntityRendererProvider.Context context) {
        super(context, new StarModel());


        withRenderLayer(new TextureLayerGeoLayer<>(this,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/base.png"),
                RenderTypes::armorCutoutNoCull){
            @Override
            protected Identifier getTextureResource(R renderState) {
                StarPatternsComponent spc = renderState.getGeckolibData(DataTickets.STAR_PATTERN);
                try {
                    for (StarPattern pattern : Patterns.patternList) {
                        if (spc.layers().getFirst().pattern().assetId().equals(pattern.assetId())) {
                            return Identifier.fromNamespaceAndPath(pattern.assetId().getNamespace(), "textures/entity/patterns/"+pattern.assetId().getPath()+".png");
                        }
                    }
                } catch (Exception ignored) {
                }
                return Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/base.png");
            }
        });
    }

    @Override
    public void addRenderData(Star animatable, @Nullable Void relatedObject, R renderState, float partialTick) {
        renderState.addGeckolibData(DataTickets.DYE_COLOR, animatable.getDyeColor());
        renderState.addGeckolibData(DataTickets.STAR_PATTERN, animatable.getSPC());
    }
}