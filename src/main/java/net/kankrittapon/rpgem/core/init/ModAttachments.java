package net.kankrittapon.rpgem.core.init;

import com.mojang.serialization.Codec;
import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.attachment.WeightData;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * Registers custom entity attachments for persistent data storage.
 */
public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister
            .create(NeoForgeRegistries.ATTACHMENT_TYPES, RPGEMCore.MODID);

    // Fail Stack: Integer, Default 0 (Used for Upgrade System)
    public static final Supplier<AttachmentType<Integer>> FAIL_STACK = ATTACHMENT_TYPES.register("fail_stack",
            () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());

    // Quest: Has Eternal Bottle Quest Started?
    public static final Supplier<AttachmentType<Boolean>> HAS_POTION_QUEST = ATTACHMENT_TYPES.register(
            "has_potion_quest",
            () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    // Weight Data: Current/Max Weight
    public static final Supplier<AttachmentType<WeightData>> WEIGHT_DATA = ATTACHMENT_TYPES.register("weight_data",
            () -> AttachmentType.builder(WeightData::new)
                    .serialize(new IAttachmentSerializer<CompoundTag, WeightData>() {
                        @Override
                        public WeightData read(net.neoforged.neoforge.attachment.IAttachmentHolder holder,
                                CompoundTag tag, net.minecraft.core.HolderLookup.Provider provider) {
                            WeightData data = new WeightData();
                            data.deserializeNBT(provider, tag);
                            return data;
                        }

                        @Override
                        public CompoundTag write(WeightData attachment,
                                net.minecraft.core.HolderLookup.Provider provider) {
                            return attachment.serializeNBT(provider);
                        }
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
