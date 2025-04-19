package com.TeamEvo.luxuryIndustries.Register;

import static com.TeamEvo.luxuryIndustries.Register.BlockReg.BLOCKS;
import static com.TeamEvo.luxuryIndustries.Register.EntityReg.BLOCK_ENTITY;
import static com.TeamEvo.luxuryIndustries.Register.TabReg.TABS;
import static com.TeamEvo.luxuryIndustries.Register.itemsReg.ITEMS;

public class LuxuryReg {
    public static void init(){
        TagReg.register();
        BLOCKS.register();
        ITEMS.register();
        TABS.register();
        BLOCK_ENTITY.register();

    }
}
