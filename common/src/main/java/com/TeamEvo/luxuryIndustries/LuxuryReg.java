package com.TeamEvo.luxuryIndustries;

import com.TeamEvo.luxuryIndustries.Blocks.BlockEntity.LockBlockEntity;
import com.TeamEvo.luxuryIndustries.Blocks.LockBlock;
import com.TeamEvo.luxuryIndustries.Items.KeyFragment;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;

public class LuxuryReg {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MODID,Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create( MODID,Registries.ITEM);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create( MODID,Registries.BLOCK_ENTITY_TYPE);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create( MODID,Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> LUXURY_TAB = TABS.register(
            "luxury_tab",
            () -> CreativeTabRegistry.create(
                    Component.translatable("category.luxury_tab"),
                    () -> new ItemStack(Items.GOLD_BLOCK)
            )
    );

    public static final RegistrySupplier<Item> ELEGANT_CASING = ITEMS.register("elegant_casing",()->new KeyFragment(new Item.Properties().arch$tab(LUXURY_TAB)));
    public static final RegistrySupplier<Item> KEY_FRAGMENT = ITEMS.register("key_shard",()->new KeyFragment(new Item.Properties().rarity(Rarity.EPIC).arch$tab(LUXURY_TAB)));
    public static final RegistrySupplier<Block> LOCK_BLOCK = BLOCKS.register("lock_block", () -> new LockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.IGNORE).explosionResistance(10f).strength(10.0F, 10F)));
    public static final RegistrySupplier<Item> LOCK_BLOCK_ITEM = ITEMS.register("lock_block",()->new BlockItem(LOCK_BLOCK.get(), new Item.Properties().arch$tab(LUXURY_TAB)));
    public static final RegistrySupplier<BlockEntityType<LockBlockEntity>> LOCK_BLOCK_ENTITY=BLOCK_ENTITY.register("lock_block_entity",
            () -> BlockEntityType.Builder.of(
        LockBlockEntity::new,
        LOCK_BLOCK.get()
).build(null));
    public static final RegistrySupplier<Block> WHITE_BRICKS = BLOCKS.register("white_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.IGNORE).explosionResistance(10f).strength(10.0F, 10F)));
    public static final RegistrySupplier<Item> WHITE_BRICKS_ITEM = ITEMS.register("white_bricks",()->new BlockItem(WHITE_BRICKS.get(), new Item.Properties().arch$tab(LUXURY_TAB)));

    public static void init(){
        BLOCKS.register();
        ITEMS.register();
        TABS.register();
        BLOCK_ENTITY.register();
    }

}
