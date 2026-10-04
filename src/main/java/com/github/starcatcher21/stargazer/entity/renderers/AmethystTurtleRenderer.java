package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.AmethystTurtle;
import com.github.starcatcher21.stargazer.entity.models.AmethystTurtleModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
/*import software.bernie.geckolib.renderer.base.GeoRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
*///? }

//? if >= 26.2 {
/*public class AmethystTurtleRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<AmethystTurtle, R> {
*///? } else {
public class AmethystTurtleRenderer extends GeoEntityRenderer<AmethystTurtle> {
//? }
    public AmethystTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new AmethystTurtleModel());
    }
}
