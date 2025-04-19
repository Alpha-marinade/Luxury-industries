package com.TeamEvo.luxuryIndustries.Items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class KeyFragment extends SmithingTemplateItem {

    public KeyFragment() {
        super(
                Component.translatable("item.luxury_industries.key.applies_to").withStyle(ChatFormatting.BLUE),
                Component.translatable("item.luxury_industries.key.ingredients").withStyle(ChatFormatting.BLUE),
                Component.translatable("item.luxury_industries.key.upgrade_desc"),
                Component.translatable("item.luxury_industries.key.base_slot_desc"),
                Component.translatable("item.luxury_industries.key.addition_slot_desc"),
                List.of( ResourceLocation.withDefaultNamespace("item/empty_slot_ingot")),
                List.of(ResourceLocation.withDefaultNamespace("item/empty_slot_ingot")),
                FeatureFlags.VANILLA);
    }


    @Override
    public int getDefaultMaxStackSize() {
        return 16;
    }

}
