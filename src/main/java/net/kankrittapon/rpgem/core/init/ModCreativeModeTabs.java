package net.kankrittapon.rpgem.core.init;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registers custom creative mode tabs.
 */
public class ModCreativeModeTabs {
    public static final ResourceKey<CreativeModeTab> RPGEM_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(RPGEMCore.MODID, "rpgem_tab"));

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, RPGEMCore.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RPGEM_TAB = CREATIVE_MODE_TABS.register(
            "rpgem_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.rpgem_core.tab"))
                    .icon(() -> new ItemStack(ModItems.ZOMBIE_HEART.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ZOMBIE_HEART.get());
                        output.accept(ModItems.BONE_OF_MAZE.get());
                        output.accept(ModItems.COSMIC_EMERALD.get());
                        output.accept(ModItems.ETHERNAL_BOTTLE.get());

                        // Forging and Upgrade items will be added via event in RPGEMForging mod
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
