package com.TeamEvo.luxuryIndustries.fabric.client;

import com.TeamEvo.luxuryIndustries.Register.EntityReg;
import com.TeamEvo.luxuryIndustries.Render.LockBlockEntityRenderer;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public final class LuxuryIndustriesFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

    }
    /*
        @Override
        public void onInitializeClient() {
            BlockEntityRendererRegistry.register(EntityReg.LOCK_BLOCK_ENTITY.get(), LockBlockEntityRenderer::new);
        }

     */

}
