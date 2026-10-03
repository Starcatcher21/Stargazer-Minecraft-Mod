package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Ghost;
import com.github.starcatcher21.stargazer.entity.models.GhostModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import com.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.jspecify.annotations.Nullable;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
//? } else {
/*import com.geckolib.renderer.layer.AutoGlowingGeoLayer;
*///? }

//? if >= 26.2 {
public class GhostRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Ghost, R> {
//? } else {
/*public class GhostRenderer extends GeoEntityRenderer<Ghost> {
*///? }
    public GhostRenderer(EntityRendererProvider.Context context) {
        super(context, new GhostModel());
        //? if >= 26.2 {
        withRenderLayer(new AutoGlowingGeoLayer<>(this));
        //? } else {
        /*addRenderLayer(new AutoGlowingGeoLayer<>(this));
        *///? }
    }

    //? if >= 26.2 {
    @Override
    public void addRenderData(Ghost animatable, @Nullable Void relatedObject, R renderState, float partialTick) {
        renderState.addGeckolibData(DataTickets.CUSTOM_NAME, animatable.CustomName);
    }
    //? }
}
