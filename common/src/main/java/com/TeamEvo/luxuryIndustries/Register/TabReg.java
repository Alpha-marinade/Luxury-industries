package com.TeamEvo.luxuryIndustries.Register;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;
import static com.TeamEvo.luxuryIndustries.Register.itemsReg.KEY_FRAGMENT;

public class TabReg {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create( MODID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> LUXURY_TAB = TABS.register(
            "luxury_tab",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP,1).title(Component.translatable("category.luxury_tab"))
                    .icon(()->new ItemStack(KEY_FRAGMENT))
                    .displayItems(((itemDisplayParameters, output) -> {
                      output.accept(KEY_FRAGMENT.get());
                        output.accept(itemsReg.LOCK_BLOCK_ITEM.get());
                        output.accept(itemsReg.WHITE_BRICKS_COLUMN_ITEM.get());
                        output.accept(itemsReg.WHITE_BRICKS_TILES_ITEM.get());
                        output.accept(itemsReg. WHITE_BRICKS_ITEM.get());
                        output.accept(itemsReg.ELEGANT_CASING.get());

                    }))
                    .build());

}
