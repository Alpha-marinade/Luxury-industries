package com.TeamEvo.luxuryIndustries.Blocks.BlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import static com.TeamEvo.luxuryIndustries.Register.EntityReg.LOCK_BLOCK_ENTITY;
import static com.TeamEvo.luxuryIndustries.Register.EntityReg.TYPEWRITTER_ENTITY;

public class TypeWriterEntity extends BlockEntity {
    public TypeWriterEntity( BlockPos blockPos, BlockState blockState) {
        super(TYPEWRITTER_ENTITY.get(), blockPos, blockState);
    }
}
