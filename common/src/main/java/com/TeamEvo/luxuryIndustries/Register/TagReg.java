package com.TeamEvo.luxuryIndustries.Register;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;


public class TagReg {
        public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
                DeferredRegister.create("luxury_industries",Registries.DATA_COMPONENT_TYPE);

        public static final RegistrySupplier<DataComponentType<Boolean>> HAS_KEY =
                COMPONENTS.register("has_key", () ->
                        DataComponentType.<Boolean>builder()
                                .persistent(Codec.BOOL)
                                .networkSynchronized(ByteBufCodecs.BOOL)
                                .build()
                );
    public static final RegistrySupplier<DataComponentType<String>> LOCK_POS =
            COMPONENTS.register("lock_pos", () ->
                    DataComponentType.<String>builder()
                            .persistent(Codec.STRING)
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                            .build()
            );
    public static final RegistrySupplier<DataComponentType<String>> KEY =
            COMPONENTS.register("key", () ->
                    DataComponentType.<String>builder()
                            .persistent(Codec.STRING)
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                            .build()
            );

        public static void register() {
            COMPONENTS.register();
        }
    }

