package com.TeamEvo.luxuryIndustries.Blocks.BlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

import static com.TeamEvo.luxuryIndustries.Register.EntityReg.LOCK_BLOCK_ENTITY;


public class LockBlockEntity extends BlockEntity {
    private String keyItem;
    private String key;
    private static final String CHARACTERS ="abcdefghijklmnopgrstuvwxyz";

    public LockBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(LOCK_BLOCK_ENTITY.get(), blockPos, blockState);
    }


    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        if(this.keyItem!=null){
            tag.putString("keyItem", this.keyItem);
        }
        if (this.key!=null){
            tag.putString("key",this.key);
        }


    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        if (tag.contains("keyItem")){
            keyItem = tag.getString("keyItem");
        }
        if (tag.contains("key")){
            key = tag.getString("key");
        }
    }

    public String getKeyItem() {
        return this.keyItem;
    }
    public String getKey() {
        return this.key;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = super.getUpdateTag(provider);
        tag.putString("keyItem", keyItem);
        tag.putString("key",key);
        return tag;
    }
    public static String keyGen(){
        Random random=new Random();
        StringBuilder builder = new StringBuilder(16);
        for (int i=0;i<16;i++){
            int index=random.nextInt(CHARACTERS.length());
            builder.append(CHARACTERS.charAt(index));
        }
        return builder.toString();
    }
    public void keygen(ItemStack stack){
        this.keyItem=stack.getItem().toString();
        this.key=keyGen();


    }

}
