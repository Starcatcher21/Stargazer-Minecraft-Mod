package com.github.starcatcher21.stargazer.entity.renderers;

import com.github.starcatcher21.stargazer.entity.BlackFox;
import com.github.starcatcher21.stargazer.entity.models.BlackFoxModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
//? if >= 26.2 {
/*import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.base.GeoRenderState;
*///? }

//? if >= 26.2 {
/*public class BlackFoxRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BlackFox, R> {
*///? } else {
public class BlackFoxRenderer extends GeoEntityRenderer<BlackFox> {
//? }
    public BlackFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new BlackFoxModel());
    }
}
