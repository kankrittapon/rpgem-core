package net.kankrittapon.rpgem.core.client;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.init.ModEntities;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = RPGEMCore.MODID, value = Dist.CLIENT)
public class RPGEMCoreClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ZOMBIE_KING.get(), ZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.SKELETON_LORD.get(), SkeletonRenderer::new);
    }
}
