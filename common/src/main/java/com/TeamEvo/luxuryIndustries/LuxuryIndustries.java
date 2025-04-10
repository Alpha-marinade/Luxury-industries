package com.TeamEvo.luxuryIndustries;

import com.TeamEvo.luxuryIndustries.datagen.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public final class LuxuryIndustries {
    public static final String MODID = "luxury_industries";
    public static void init() {
      LuxuryReg.init();
    }

}
