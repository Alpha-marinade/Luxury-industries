package com.TeamEvo.luxuryIndustries.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class AnvilPushMixin {
    @Inject(method = "getPistonPushReaction", at = @At("HEAD"), cancellable = true)
    public void modifyPushReaction(CallbackInfoReturnable<PushReaction> cir) {
        if (((BlockState) (Object) this).getBlock() instanceof AnvilBlock) {
            cir.setReturnValue(PushReaction.PUSH_ONLY);
        }
    }

}