package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.Rook;
import com.github.starcatcher21.stargazer.entity.models.RookModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
/*import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? }

//? if >= 26.2 {
/*public class RookRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<Rook, R> {
*///? } else {
public class RookRenderer extends GeoEntityRenderer<Rook> {
//? }
    public RookRenderer(EntityRendererProvider.Context context) {
        super(context, new RookModel());
    }
}
