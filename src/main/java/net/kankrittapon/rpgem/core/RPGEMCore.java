package net.kankrittapon.rpgem.core;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(RPGEMCore.MODID)
public class RPGEMCore {
    public static final String MODID = "rpgem_core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RPGEMCore(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("RPGEM Core Initializing...");

        // Registries
        net.kankrittapon.rpgem.core.init.ModAttributes.register(modEventBus);
        net.kankrittapon.rpgem.core.init.ModMobEffects.register(modEventBus);
        net.kankrittapon.rpgem.core.init.ModCreativeModeTabs.register(modEventBus);

        // Networking
        modEventBus.addListener(net.kankrittapon.rpgem.core.network.ModMessages::register);

        // Config
        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON,
                net.kankrittapon.rpgem.core.config.Config.SPEC);
        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.CLIENT,
                net.kankrittapon.rpgem.core.config.ClientConfig.SPEC);

        LOGGER.info("RPGEM Core Initialized");
    }
}
