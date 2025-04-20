package com.TeamEvo.luxuryIndustries.Register;


import com.TeamEvo.luxuryIndustries.Items.KeyFragment;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import static com.TeamEvo.luxuryIndustries.Register.BlockReg.*;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;
import static com.TeamEvo.luxuryIndustries.Register.TabReg.LUXURY_TAB;

public class itemsReg {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create( MODID,Registries.ITEM);

    public static final RegistrySupplier<Item> ELEGANT_CASING = ITEMS.register("elegant_casing",()->new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> KEY_FRAGMENT = ITEMS.register("key_shard",()->new KeyFragment());
    public static final RegistrySupplier<Item> LOCK_BLOCK_ITEM = ITEMS.register("lock_block",()->new BlockItem(LOCK_BLOCK.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> WHITE_BRICKS_ITEM = ITEMS.register("white_bricks",()->new BlockItem(WHITE_BRICKS.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> WHITE_BRICKS_COLUMN_ITEM = ITEMS.register("white_bricks_column",()->new BlockItem(WHITE_BRICKS_COLUMN.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> WHITE_BRICKS_TILES_ITEM = ITEMS.register("white_bricks_tiles",()->new BlockItem(WHITE_BRICKS_TILES.get(), new Item.Properties()));

}
