package com.github.starcatcher21.stargazer.entity.rederStates;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.Map;

public class GhostEntityRenderState extends LivingEntityRenderState implements GeoRenderState  {
    public GhostEntityRenderState() {
        super();
    }

    public String customNameString = "";

    @Override
    public Map<DataTicket<?>, Object> getDataMap() {
        return Map.of(DataTicket.create("name", String.class), customNameString);
    }
}
