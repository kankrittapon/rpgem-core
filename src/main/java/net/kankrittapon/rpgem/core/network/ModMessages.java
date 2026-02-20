package net.kankrittapon.rpgem.core.network;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModMessages {
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(RPGEMCore.MODID);

        registrar.playToClient(
                PacketSyncFailStack.TYPE,
                PacketSyncFailStack.STREAM_CODEC,
                PacketSyncFailStack::handle);

        registrar.playToClient(
                PacketSyncWeight.TYPE,
                PacketSyncWeight.STREAM_CODEC,
                PacketSyncWeight::handle);
    }
}
