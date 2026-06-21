package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Ghost;
import com.github.starcatcher21.stargazer.entity.models.GhostModel;
import com.github.starcatcher21.stargazer.entity.rederStates.GhostEntityRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;

public class GhostRenderer extends GeoEntityRenderer<Ghost, GhostEntityRenderState> {

    public GhostRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostModel());
        withRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public GhostEntityRenderState createRenderState(Ghost animatable, @Nullable Void relatedObject) {
        return new GhostEntityRenderState();
    }

    @Override
    public void updateRenderState(Ghost entity, GhostEntityRenderState state, float tickProgress) {
        try {
            super.updateRenderState(entity, state, tickProgress);
        } catch (Exception ignored) {}
        if (entity.hasCustomName()) {
            state.customNameString = entity.getStringifiedName().toLowerCase();
        } else {
            state.customNameString = "";
        }
    }
}