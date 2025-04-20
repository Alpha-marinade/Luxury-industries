package com.TeamEvo.luxuryIndustries.neoforge;

import com.TeamEvo.luxuryIndustries.LuxuryIndustries;
import com.TeamEvo.luxuryIndustries.Register.EntityReg;
import com.TeamEvo.luxuryIndustries.Render.LockBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;

@Mod(MODID)
public final class LuxuryIndustriesNeoForge {
    public LuxuryIndustriesNeoForge() {

        LuxuryIndustries.init();
    }
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public class ClientSetup {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(EntityReg.LOCK_BLOCK_ENTITY.get(), LockBlockEntityRenderer::new);
        }
    }

}
