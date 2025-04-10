package com.TeamEvo.luxuryIndustries.Blocks;

import com.TeamEvo.luxuryIndustries.LuxuryReg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class ColumnBlock extends Block {
    public static final BooleanProperty UP=BooleanProperty.create("up");
    public static final BooleanProperty DOWN=BooleanProperty.create("down");

    public ColumnBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(UP,false)
                .setValue(DOWN,false));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        updateState(state,level,pos);

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP,DOWN);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState newState = this.getStateDefinition().any();
        boolean hasUp = ctx.getLevel().getBlockState(ctx.getClickedPos().above()).is(LuxuryReg.WHITE_BRICKS_COLUMN.get());
        newState = newState.setValue(UP, hasUp);
        boolean hasDown = ctx.getLevel().getBlockState(ctx.getClickedPos().below()).is(LuxuryReg.WHITE_BRICKS_COLUMN.get());
        newState = newState.setValue(DOWN, hasDown);
        return newState;
    }

    public void updateState(BlockState state, Level level, BlockPos pos){
        if (!level.isClientSide) {
            BlockState newState = state;
            boolean hasUp = level.getBlockState(pos.above()).is(LuxuryReg.WHITE_BRICKS_COLUMN.get());
            newState = newState.setValue(UP, hasUp);

            boolean hasDown = level.getBlockState(pos.below()).is(LuxuryReg.WHITE_BRICKS_COLUMN.get());
            newState = newState.setValue(DOWN, hasDown);

            if (!newState.equals(state)) {
                level.setBlock(pos, newState, Block.UPDATE_CLIENTS);
            }
        }
    }
}
