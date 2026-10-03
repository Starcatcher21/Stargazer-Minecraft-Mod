package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.BlackRook;
import com.github.starcatcher21.stargazer.entity.models.BlackRookModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import com.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
import com.geckolib.renderer.base.GeoRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
//? }

//? if >= 26.2 {
public class BlackRookRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BlackRook, R> {
//? } else {
/*public class BlackRookRenderer extends GeoEntityRenderer<BlackRook> {
*///? }
    public BlackRookRenderer(EntityRendererProvider.Context context) {
        super(context, new BlackRookModel());
    }
}
