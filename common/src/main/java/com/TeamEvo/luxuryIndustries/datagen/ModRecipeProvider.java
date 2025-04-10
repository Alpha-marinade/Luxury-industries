package com.TeamEvo.luxuryIndustries.datagen;// Импорты
import com.TeamEvo.luxuryIndustries.LuxuryReg;
import dev.architectury.registry.registries.Registrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {


    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }



    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, LuxuryReg)
                .pattern("##")
                .pattern("##")
                .define('#', Items.OAK_PLANKS)
                .unlockedBy("has_planks", has(Items.OAK_PLANKS))
                .save(recipeOutput, "yourmodid:crafting_table_from_oak_planks");



    }
}