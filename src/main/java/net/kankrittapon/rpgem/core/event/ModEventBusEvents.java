package net.kankrittapon.rpgem.core.event;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.init.ModAttributes;
import net.kankrittapon.rpgem.core.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = RPGEMCore.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    /**
     * Attach custom RPG attributes to Player entity.
     */
    @SubscribeEvent
    public static void onAttributeModification(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.LIFE_STEAL);
        event.add(EntityType.PLAYER, ModAttributes.CRIT_CHANCE);
        event.add(EntityType.PLAYER, ModAttributes.ARMOR_PENETRATION);
        event.add(EntityType.PLAYER, ModAttributes.EVASION);
        event.add(EntityType.PLAYER, ModAttributes.DAMAGE_REDUCTION);
        event.add(EntityType.PLAYER, ModAttributes.REFLECT_CHANCE);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ZOMBIE_KING.get(),
                net.kankrittapon.rpgem.core.entity.custom.ZombieKing.createAttributes().build());
        event.put(ModEntities.SKELETON_LORD.get(),
                net.kankrittapon.rpgem.core.entity.custom.SkeletonLord.createAttributes().build());
    }

    /**
     * Register spawn placements for custom monster entities.
     * จำเป็นต้องทำ ไม่งั้น NeoForge จะ throw ERROR ว่า entity ไม่มี spawn
     * restriction
     */
    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.ZOMBIE_KING.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.SKELETON_LORD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
