package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.EyeBat;
import com.github.starcatcher21.stargazer.entity.models.EyeBatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
/*import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? }

//? if >= 26.2 {
/*public class EyeBatRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<EyeBat, R> {
*///? } else {
public class EyeBatRenderer extends GeoEntityRenderer<EyeBat> {
//? }
    public EyeBatRenderer(EntityRendererProvider.Context context) {
        super(context, new EyeBatModel());
    }
}
