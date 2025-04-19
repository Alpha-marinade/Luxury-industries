package com.TeamEvo.luxuryIndustries.Register;

import com.TeamEvo.luxuryIndustries.Recipes.AnvilCraftingRecipe;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;

public class RecipesReg {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE =
            DeferredRegister.create(MODID,Registries.RECIPE_TYPE);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(MODID,Registries.RECIPE_SERIALIZER);
    public static final RegistrySupplier<RecipeType<AnvilCraftingRecipe>> ANVIL_CRAFTING_TYPE =
            RECIPE_TYPE.register("anvil_crafting",
                    () -> new RecipeType<>() {
                        public String toString() { return "anvil_crafting"; }
                    });

    public static final RegistrySupplier<RecipeSerializer<AnvilCraftingRecipe>> ANVIL_CRAFTING_SERIALIZER =
            RECIPE_SERIALIZER.register("anvil_crafting",
                    () -> AnvilCraftingRecipe.Serializer.INSTANCE);

    public static void register() {
        RECIPE_TYPE.register();
        RECIPE_SERIALIZER.register();
    }
}