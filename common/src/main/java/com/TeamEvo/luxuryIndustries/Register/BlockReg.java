package com.TeamEvo.luxuryIndustries.Register;

import com.TeamEvo.luxuryIndustries.Blocks.ColumnBlock;
import com.TeamEvo.luxuryIndustries.Blocks.LockBlock;
import com.TeamEvo.luxuryIndustries.Blocks.TypeWriter;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;

public class BlockReg {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MODID, Registries.BLOCK);

    public static final RegistrySupplier<Block> LOCK_BLOCK = BLOCKS.register("lock_block", () -> new LockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.IGNORE).explosionResistance(10f).strength(10.0F, 10F).randomTicks()));
    public static final RegistrySupplier<Block> TYPEWRITTER = BLOCKS.register("typewritter", () -> new TypeWriter(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.IGNORE).explosionResistance(10f).strength(10.0F, 10F).randomTicks()));

    public static final RegistrySupplier<Block> WHITE_BRICKS = BLOCKS.register("white_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.IGNORE).explosionResistance(0f).strength(1.0F, 1F)));
    public static final RegistrySupplier<Block> WHITE_BRICKS_COLUMN = BLOCKS.register("white_bricks_column", () -> new ColumnBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.NORMAL).explosionResistance(1f).strength(1.0F, 1F)));
    public static final RegistrySupplier<Block> WHITE_BRICKS_TILES = BLOCKS.register("white_bricks_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.NORMAL).explosionResistance(1f).strength(1.0F, 1F)));
}