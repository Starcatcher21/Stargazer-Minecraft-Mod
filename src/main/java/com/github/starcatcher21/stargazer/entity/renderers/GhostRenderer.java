package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.github.starcatcher21.stargazer.entity.Ghost;
import com.github.starcatcher21.stargazer.entity.models.GhostModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;

public class GhostRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Ghost, R> {
    public GhostRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostModel());
        withRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public void addRenderData(Ghost animatable, @Nullable Void relatedObject, R renderState, float partialTick) {
        renderState.addGeckolibData(DataTickets.CUSTOM_NAME, animatable.CustomName);
    }
}