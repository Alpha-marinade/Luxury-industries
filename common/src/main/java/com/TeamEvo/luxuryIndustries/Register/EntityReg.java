package com.TeamEvo.luxuryIndustries.Register;

import com.TeamEvo.luxuryIndustries.Blocks.BlockEntity.LockBlockEntity;
import com.TeamEvo.luxuryIndustries.Blocks.BlockEntity.TypeWriterEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.TeamEvo.luxuryIndustries.LuxuryIndustries.MODID;
import static com.TeamEvo.luxuryIndustries.Register.BlockReg.LOCK_BLOCK;
import static com.TeamEvo.luxuryIndustries.Register.BlockReg.TYPEWRITTER;

public class EntityReg {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create( MODID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<LockBlockEntity>> LOCK_BLOCK_ENTITY=BLOCK_ENTITY.register("lock_block_entity",
            () -> BlockEntityType.Builder.of(
                    LockBlockEntity::new,
                    LOCK_BLOCK.get()
            ).build(null));
    public static final RegistrySupplier<BlockEntityType<TypeWriterEntity>> TYPEWRITTER_ENTITY=BLOCK_ENTITY.register("typewritter_entity",
            () -> BlockEntityType.Builder.of(
                    TypeWriterEntity::new,
                    TYPEWRITTER.get()
            ).build(null));
}
