package com.TeamEvo.luxuryIndustries.Blocks.BlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import static com.TeamEvo.luxuryIndustries.LuxuryReg.LOCK_BLOCK_ENTITY;

public class LockBlockEntity extends BlockEntity {
    private int Key;
    public LockBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(LOCK_BLOCK_ENTITY.get(), blockPos, blockState);
        this.Key=0;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putInt("key", this.Key);
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        this.Key = compoundTag.getInt("key");
        System.out.println(Key);
    }


}
