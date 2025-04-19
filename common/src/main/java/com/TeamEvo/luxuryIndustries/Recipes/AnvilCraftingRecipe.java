package com.TeamEvo.luxuryIndustries.Recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnvilCraftingRecipe implements Recipe<RecipeInput> {
    private final ResourceLocation id;
    public final List<ItemStack> inputs;
    private final ItemStack output;

    public AnvilCraftingRecipe(ResourceLocation id, List<ItemStack> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(RecipeInput recipeInput, Level level) {

        return false;
    }

    @Override
    public ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    public boolean matches(List<ItemStack> items) {
        Map<Item, Integer> required = inputs.stream()
                .collect(Collectors.toMap(
                        ItemStack::getItem,
                        ItemStack::getCount,
                        Integer::sum
                ));

        Map<Item, Integer> actual = items.stream()
                .collect(Collectors.toMap(
                        ItemStack::getItem,
                        ItemStack::getCount,
                        Integer::sum
                ));

        return required.entrySet().stream()
                .allMatch(entry -> actual.getOrDefault(entry.getKey(), 0) >= entry.getValue());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }


    public ResourceLocation getId() {
        System.out.println(id);
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AnvilCraftingRecipe> {
        public static final Type INSTANCE = new Type();
        private Type() {}
    }

    public static class Serializer implements RecipeSerializer<AnvilCraftingRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final MapCodec<AnvilCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        ResourceLocation.CODEC.fieldOf("id").forGetter(AnvilCraftingRecipe::getId),
                        ItemStack.CODEC.listOf().fieldOf("inputs").forGetter(r -> r.inputs),
                        ItemStack.CODEC.fieldOf("output").forGetter(r -> r.output)
                ).apply(instance, AnvilCraftingRecipe::new)
        );

        @Override
        public MapCodec<AnvilCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AnvilCraftingRecipe> streamCodec() {
            return StreamCodec.of(
                    this::encode,
                    this::decode
            );
        }

        private void encode(RegistryFriendlyByteBuf buf, AnvilCraftingRecipe recipe) {
            buf.writeResourceLocation(recipe.id);
            buf.writeCollection(
                    recipe.inputs,
                    (b, item) -> ItemStack.STREAM_CODEC.encode((RegistryFriendlyByteBuf) b, item)
            );
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
        }

        private AnvilCraftingRecipe decode(RegistryFriendlyByteBuf buf) {
            ResourceLocation id = buf.readResourceLocation();
            List<ItemStack> inputs = buf.readCollection(
                    ArrayList::new,
                    b -> ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) b)
            );
            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            return new AnvilCraftingRecipe(id, inputs, output);
        }
    }
}