package net.kankrittapon.rpgem.core.init;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, RPGEMCore.MODID);

    /**
     * The key used by all RPGEM sub-modules to append their items into this tab via
     * BuildCreativeModeTabContentsEvent.
     */
    public static final ResourceKey<CreativeModeTab> RPGEM_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(RPGEMCore.MODID, "rpgem_tab"));

    static {
        CREATIVE_MODE_TABS.register("rpgem_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.rpgem_core.rpgem_tab"))
                .icon(() -> Items.ENDER_EYE.getDefaultInstance())
                .build());
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
