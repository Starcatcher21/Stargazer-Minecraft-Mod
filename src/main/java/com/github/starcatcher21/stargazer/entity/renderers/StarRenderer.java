package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.entity.models.StarModel;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPattern;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
/*import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.jspecify.annotations.Nullable;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.builtin.TextureLayerGeoLayer;
*///? } else {
//? }

//? if >= 26.2 {
/*public class StarRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<Star, R> {
*///? } else {
public class StarRenderer extends GeoEntityRenderer<Star> {
//? }
    public static ResourceLocation BASE =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/base.png");
    public static ResourceLocation MOON =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/moon.png");
    public static ResourceLocation PONK =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/ponk.png");
    public static ResourceLocation PACMAN =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/pacman.png");
    public static ResourceLocation SUN =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/sun.png");
    public static ResourceLocation FISH =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/fish.png");
    public static ResourceLocation BRICK =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/brick.png");
    public static ResourceLocation CREEPER =ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/creeper.png");
    public StarRenderer(EntityRendererProvider.Context context) {
        super(context, new StarModel());


        //? if >= 26.2 {
        /*withRenderLayer(new TextureLayerGeoLayer<>(this,
              ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/base.png"),
                RenderTypes::armorCutoutNoCull){
            @Override
            protected ResourceLocation getTextureResource(R renderState) {
                StarPatternsComponent spc = renderState.getGeckolibData(DataTickets.STAR_PATTERN);
                try {
                    for (StarPattern pattern : Patterns.patternList) {
                        if (spc.layers().getFirst().pattern().assetId().equals(pattern.assetId())) {
                            return ResourceLocation.fromNamespaceAndPath(pattern.assetId().getNamespace(), "textures/entity/patterns/"+pattern.assetId().getPath()+".png");
                        }
                    }
                } catch (Exception ignored) {
                }
                return ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/entity/patterns/base.png");
            }
        });
        *///? } else {
        //? }
    }

    //? if >= 26.2 {
    /*@Override
    public void addRenderData(Star animatable, @Nullable Void relatedObject, R renderState, float partialTick) {
        renderState.addGeckolibData(DataTickets.DYE_COLOR, animatable.getDyeColor());
        renderState.addGeckolibData(DataTickets.STAR_PATTERN, animatable.getSPC());
    }
    *///? }
}
