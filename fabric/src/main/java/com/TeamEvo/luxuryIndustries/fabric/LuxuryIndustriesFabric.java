package com.TeamEvo.luxuryIndustries.fabric;

import com.TeamEvo.luxuryIndustries.LuxuryIndustries;
import net.fabricmc.api.ModInitializer;

import static com.TeamEvo.luxuryIndustries.LuxuryReg.*;

public final class LuxuryIndustriesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LuxuryIndustries.init();

    }
}
