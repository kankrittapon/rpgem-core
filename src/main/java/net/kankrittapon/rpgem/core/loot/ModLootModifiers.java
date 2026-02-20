package net.kankrittapon.rpgem.core.loot;

import com.mojang.serialization.MapCodec;
import net.kankrittapon.rpgem.core.RPGEMCore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * Registers custom loot modifiers.
 */
public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister
            .create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RPGEMCore.MODID);

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM = LOOT_MODIFIER_SERIALIZERS.register("add_item",
            AddItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
