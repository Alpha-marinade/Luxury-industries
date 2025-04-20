package com.TeamEvo.luxuryIndustries.Blocks;

import com.TeamEvo.luxuryIndustries.Blocks.BlockEntity.LockBlockEntity;
import com.TeamEvo.luxuryIndustries.Register.TagReg;
import dev.architectury.networking.SpawnEntityPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
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
    private static final BooleanProperty HAS_KEY =BooleanProperty.create("has_key");
    private static final BooleanProperty MODE=BooleanProperty.create("mode");
    public LockBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(OPENED,false)
                .setValue(HAS_KEY,false)
                .setValue(MODE,false));
    }
@Override
protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        LockBlockEntity blockEntity = (LockBlockEntity) level.getBlockEntity(blockPos);
        boolean hasKey = blockState.getValue(HAS_KEY);

        boolean actionPerformed = false;
        if (!hasKey) {
            if (blockEntity != null && blockEntity.getKeyItem() == null && itemStack.getComponents().has(TagReg.HAS_KEY.get())) {
                itemStack.set(TagReg.X_POS.get(), blockPos.getX());
                itemStack.set(TagReg.Y_POS.get(), blockPos.getY());
                itemStack.set(TagReg.Z_POS.get(), blockPos.getZ());
                blockEntity.keygen(itemStack);
                BlockState newState = blockState.setValue(HAS_KEY, true);
                level.setBlock(blockPos, newState, Block.UPDATE_ALL);
                actionPerformed = true;
                ClientMessage(Component.translatable("ui.luxury_industries.setKey"),player);
            }
        } else {
            if (itemStack.has(TagReg.KEY.get()) && (itemStack.get(TagReg.KEY.get()).equals(((LockBlockEntity) level.getBlockEntity(blockPos)).getKey()))) {

                BlockState newState = blockState.cycle(OPENED);
                if (blockState.getValue(OPENED)) {
                    ClientMessage(Component.translatable("ui.luxury_industries.opened"), player);
                } else {
                    ClientMessage(Component.translatable("ui.luxury_industries.closed"), player);
                }
                level.setBlock(blockPos, newState, Block.UPDATE_ALL);
                actionPerformed = true;
            }
        }
        return actionPerformed ? ItemInteractionResult.SUCCESS : super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);

}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPENED);
        builder.add(HAS_KEY);
        builder.add(MODE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LockBlockEntity(blockPos, blockState);
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return blockState.getValue(OPENED)?15:0;
    }


    @Override
    protected boolean isSignalSource(BlockState blockState) {
        return true;
    }

    public void ClientMessage(Component text, Player player){
        player.displayClientMessage(text,true);
    }

}

