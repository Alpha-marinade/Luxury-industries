package com.TeamEvo.luxuryIndustries.Blocks.BlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import static com.TeamEvo.luxuryIndustries.Register.EntityReg.TYPEWRITER_ENTITY;

public class TypeWriterEntity extends BlockEntity {
    public TypeWriterEntity( BlockPos blockPos, BlockState blockState) {
        super(TYPEWRITER_ENTITY.get(), blockPos, blockState);
    }
}
