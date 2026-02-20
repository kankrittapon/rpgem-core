package net.kankrittapon.rpgem.core.network;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModMessages {
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(RPGEMCore.MODID);

        // Core packets go here (e.g. Syncing some global state)
        // registrar.playToClient(...)
    }
}
