package com.TeamEvo.luxuryIndustries.Blocks.BlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.random.RandomGenerator;

import static com.TeamEvo.luxuryIndustries.LuxuryReg.LOCK_BLOCK_ENTITY;

public class LockBlockEntity extends BlockEntity {
    private int key = 0;

    public LockBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(LOCK_BLOCK_ENTITY.get(), blockPos, blockState);
    }


    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putInt("key", key);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        key = tag.getInt("key");
    }


    public void generateKey() {
        key = level.getRandom().nextInt(10000000, 99999999);
        System.out.println(key);
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    public int getKey() {
        return key;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = super.getUpdateTag(provider);
        tag.putInt("key", key);
        return tag;
    }

}
