package com.TeamEvo.luxuryIndustries.Blocks;

import com.TeamEvo.luxuryIndustries.Blocks.BlockEntity.LockBlockEntity;
import com.TeamEvo.luxuryIndustries.Register.TagReg;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class LockBlock extends Block implements EntityBlock {
    private static final BooleanProperty OPENED=BooleanProperty.create("opened");
    public LockBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(OPENED,false));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        LockBlockEntity blockEntity = (LockBlockEntity ) level.getBlockEntity(blockPos);
        if (blockEntity != null) {
            if (blockEntity.getKeyItem() ==null&& itemStack.getComponents().has(TagReg.HAS_KEY.get())) {
                String pos = "X: "+blockPos.getX()+" Y:"+blockPos.getY()+" Z: "+blockPos.getZ();
                itemStack.set(TagReg.LOCK_POS.get(),pos);

                blockEntity.keygen(itemStack);
            }
        }

        BlockState state=blockState.cycle(OPENED);
        level.setBlock(blockPos,state,Block.UPDATE_ALL);
        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPENED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LockBlockEntity(blockPos, blockState);
    }
}
