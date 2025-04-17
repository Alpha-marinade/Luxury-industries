package com.TeamEvo.luxuryIndustries.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;

public class Lamp extends Block {

    public Lamp(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        updateLightBlock(level, pos);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
        if (!level.isClientSide) {
            BlockPos lightPos = findLightBlockPos(level, pos);
            if (lightPos != null) {
                level.removeBlock(lightPos, false);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        updateLightBlock(level, pos);
    }

    private void updateLightBlock(Level level, BlockPos lampPos) {
        if (!level.isClientSide) {
            BlockPos lightPos = findLightBlockPos(level, lampPos);
            if (lightPos != null) {
                level.removeBlock(lightPos, false);
            }

            BlockPos belowPos = lampPos.below();
            while (belowPos.getY() > level.getMinBuildHeight()) {
                BlockState belowState = level.getBlockState(belowPos);
                if (!belowState.isAir()) {
                    BlockPos abovePos = belowPos.above();
                    if (level.getBlockState(abovePos).isAir()) {
                        level.setBlock(abovePos, Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 15), 3);
                    }
                    break;
                }
                belowPos = belowPos.below();
            }
        }
    }

    private BlockPos findLightBlockPos(Level level, BlockPos lampPos) {
        BlockPos belowPos = lampPos.below();
        while (belowPos.getY() > level.getMinBuildHeight()) {
            BlockState belowState = level.getBlockState(belowPos);
            if (!belowState.isAir()) {
                BlockPos abovePos = belowPos.above();
                if (level.getBlockState(abovePos).is(Blocks.LIGHT)) {
                    return abovePos;
                }
                break;
            }
            belowPos = belowPos.below();
        }
        return null;
    }
}