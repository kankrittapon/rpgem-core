package net.kankrittapon.rpgem.core.network;

import io.netty.buffer.ByteBuf;
import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.init.ModAttachments;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Packet to sync the Fail Stack value from server to client.
 */
public record PacketSyncFailStack(int failStack) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PacketSyncFailStack> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(RPGEMCore.MODID, "sync_fail_stack"));

    public static final StreamCodec<ByteBuf, PacketSyncFailStack> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, PacketSyncFailStack::failStack,
            PacketSyncFailStack::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final PacketSyncFailStack message, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isClientbound()) {
                context.player().setData(ModAttachments.FAIL_STACK, message.failStack());
            }
        });
    }
}
