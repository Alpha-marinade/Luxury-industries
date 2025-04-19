package com.TeamEvo.luxuryIndustries.mixin;

import com.TeamEvo.luxuryIndustries.Register.TagReg;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemsMixin {
    @Inject(method = "appendHoverText",at=@At("TAIL"))
    public void tooltip(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag, CallbackInfo ci){
        if(itemStack.getComponents().has(TagReg.HAS_KEY.get())){
           list.add(Component.translatable("item.luxury_industries.items.key").withStyle(ChatFormatting.GOLD));
        }
        if(itemStack.getComponents().has(TagReg.LOCK_POS.get())){
            list.add(Component.translatable("item.luxury_industries.items.linked_to",itemStack.get(TagReg.LOCK_POS.get())).withStyle(ChatFormatting.GRAY));
        }
    }
}
