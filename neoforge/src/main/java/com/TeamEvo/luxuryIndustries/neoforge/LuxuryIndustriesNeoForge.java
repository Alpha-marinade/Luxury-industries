package com.TeamEvo.luxuryIndustries.neoforge;

import com.TeamEvo.luxuryIndustries.LuxuryIndustries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(LuxuryIndustries.MODID)
public final class LuxuryIndustriesNeoForge {
    public LuxuryIndustriesNeoForge() {

        LuxuryIndustries.init();
    }

}
