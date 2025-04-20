package com.TeamEvo.luxuryIndustries.mixin;

import com.TeamEvo.luxuryIndustries.Recipes.AnvilCraftingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(AnvilBlock.class)
public class AnvilMixin {
    @Inject(method = "onLand", at = @At("TAIL"))
    private void onAnvilLand(Level level, BlockPos pos, BlockState state, BlockState newState, FallingBlockEntity entity, CallbackInfo ci) {
        if (level.isClientSide) return;

        List<ItemStack> items = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos))
                .stream()
                .map(ItemEntity::getItem)
                .collect(Collectors.toList());

        level.getRecipeManager().getAllRecipesFor(AnvilCraftingRecipe.Type.INSTANCE)
                .stream()
                .filter(holder -> holder.value().matches(items))
                .findFirst()
                .ifPresent(holder -> {
                    consumeItems(level, pos, holder.value());
                    ItemStack result = holder.value().getResultItem(level.registryAccess());
                    level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), result));
                });
    }

    private void consumeItems(Level level, BlockPos pos, AnvilCraftingRecipe recipe) {
        Map<Item, Integer> required = recipe.inputs.stream()
                .collect(Collectors.toMap(
                        ItemStack::getItem,
                        ItemStack::getCount,
                        Integer::sum));

        level.getEntitiesOfClass(ItemEntity.class, new AABB(pos)).forEach(e -> {
            ItemStack stack = e.getItem();
            Item item = stack.getItem();
            if (required.containsKey(item)) {
                int remove = Math.min(stack.getCount(), required.get(item));
                stack.shrink(remove);
                required.put(item, required.get(item) - remove);
                if (stack.isEmpty()) e.discard();
            }
        });
    }

}
/*
@Mixin(AnvilBlock.class)
public class AnvilMixin  {
    @Inject(method = "onLand",at=@At("TAIL"))
    public void onBreak(Level level, BlockPos blockPos, BlockState blockState, BlockState blockState2, FallingBlockEntity fallingBlockEntity, CallbackInfo ci){
        ArrayList<ItemStack> stacks=new ArrayList<>();
        AABB area=new AABB(blockPos);
        List<Entity> entities=level.getEntities(null,area);
        for(int i=0;i<entities.size();i++){
            if(entities.get(i)instanceof ItemEntity){
                stacks.add(((ItemEntity) entities.get(i)).getItem());
            }
        }
        ItemEntity entity= new ItemEntity(level,blockPos.getX(),blockPos.getY(),blockPos.getZ(), Items.DIAMOND.getDefaultInstance());
        level.addFreshEntity(entity);
        System.out.println(stacks);

    }
}

 */



