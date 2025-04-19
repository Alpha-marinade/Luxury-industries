package com.TeamEvo.luxuryIndustries.mixin;

import com.TeamEvo.luxuryIndustries.Register.TagReg.*;
import com.TeamEvo.luxuryIndustries.Register.itemsReg;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.swing.*;
import java.util.List;
import java.util.function.Predicate;

import static com.TeamEvo.luxuryIndustries.Register.TagReg.HAS_KEY;

// SmithingMenuMixin.java
@Mixin(SmithingMenu.class)
public abstract class SmithingTableCraftMixin extends ItemCombinerMenu {
    @Shadow @Final private List<RecipeHolder<SmithingRecipe>> recipes;




    protected SmithingTableCraftMixin(MenuType<?> menuType, int i, Inventory inventory, ContainerLevelAccess access) {
        super(menuType, i, inventory, access);
    }

    @Unique
    private boolean isCustomCraft;

    @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
    private void modifyResult(CallbackInfo ci) {
        Container input = this.inputSlots;

        ItemStack template = input.getItem(0);
        ItemStack base = input.getItem(1);
        ItemStack addition = input.getItem(2);

        if (template.getItem() == itemsReg.KEY_FRAGMENT.get()
                && !base.isEmpty()
                && addition.isEmpty()) {

            ItemStack result = base.copyWithCount(1);
            result.set(HAS_KEY.get(), true);

            this.resultSlots.setItem(0, result);
            isCustomCraft = true;
            ci.cancel();
        }
    }

    @Inject(method = "onTake", at = @At("TAIL"))
    private void consumeItems(Player player, ItemStack stack, CallbackInfo ci) {
        if (isCustomCraft) {
            this.inputSlots.getItem(0).shrink(1);
            this.inputSlots.getItem(1).shrink(1);
           isCustomCraft = false;
        }
    }
    @Redirect(
            method = "createInputSlotDefinitions",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$Builder;withSlot(IIILjava/util/function/Predicate;)Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$Builder;"
            ))
    private ItemCombinerMenuSlotDefinition.Builder redirectSlotValidation(ItemCombinerMenuSlotDefinition.Builder builder, int slot, int x, int y, Predicate<ItemStack> predicate) {
        return builder.withSlot(slot, x, y, itemStack -> true);
    }
    /*
    @Redirect(
            method = "mayPickup",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/SmithingRecipe;matches(Lnet/minecraft/world/item/crafting/SmithingRecipeInput;Lnet/minecraft/world/level/Level;)Z"
            )
    )
    private boolean allowPickup(SmithingRecipe recipe, SmithingRecipeInput input, Level level) {
        return isCustomCraft || recipe.matches(input, level);
    }

     */
}
