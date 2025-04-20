package com.TeamEvo.luxuryIndustries.Blocks;

import com.TeamEvo.luxuryIndustries.Blocks.BlockEntity.TypeWriterEntity;
import com.TeamEvo.luxuryIndustries.Register.itemsReg;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class TypeWriter extends Block implements EntityBlock {
    private static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);
    private static final BooleanProperty HAS_PAPER = BooleanProperty.create("has_paper");
    private static int clicks = 0;
    private static int  shouldClicks ;

    public TypeWriter(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 0).setValue(HAS_PAPER, false));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TypeWriterEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
        builder.add(HAS_PAPER);
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!blockState.getValue(HAS_PAPER)) {
            if (itemStack.is(Items.PAPER)) {
                Random random = new Random();
                shouldClicks= random.nextInt(1, 10);
                BlockState state = blockState.setValue(HAS_PAPER, true);
                level.setBlock(blockPos, state, UPDATE_ALL);
                itemStack.shrink(1);
                return ItemInteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        BlockState state;
        if (player.isShiftKeyDown()) {
            if (blockState.getValue(TYPE) < 2) {
                state = blockState.setValue(TYPE, blockState.getValue(TYPE) + 1);
            } else {
                state = blockState.setValue(TYPE, 0);
            }
            level.setBlock(blockPos, state, UPDATE_ALL);
            return InteractionResult.SUCCESS;
        } else {
            if (blockState.getValue(HAS_PAPER)) {
                if (clicks < shouldClicks) {
                    clicks++;
                } else {
                    clicks = 0;
                    craft(blockState,level,blockPos);
                    return InteractionResult.SUCCESS;
                }

            }
        }
        return InteractionResult.SUCCESS;
    }
    public void craft(BlockState state,Level level,BlockPos pos){
        BlockState ne=state.setValue(HAS_PAPER,false);
        level.setBlock(pos,ne,UPDATE_ALL);
        ItemEntity entity=new ItemEntity(level,pos.getX(),pos.getY()+1,pos.getZ(), Items.PAPER.getDefaultInstance());;
        switch (state.getValue(TYPE)){
            case 0-> {
                entity=new ItemEntity(level,pos.getX(),pos.getY()+1,pos.getZ(), itemsReg.RUNE_A.get().getDefaultInstance());
            }
            case 1-> {
                entity=new ItemEntity(level,pos.getX(),pos.getY()+1,pos.getZ(), itemsReg.RUNE_B.get().getDefaultInstance());
            }
            case 2-> {
                entity=new ItemEntity(level,pos.getX(),pos.getY()+1,pos.getZ(), itemsReg.RUNE_C.get().getDefaultInstance());
            }
        }
        level.addFreshEntity(entity);
    }
}

