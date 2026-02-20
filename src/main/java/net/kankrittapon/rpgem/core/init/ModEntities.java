package net.kankrittapon.rpgem.core.init;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.entity.custom.SkeletonLord;
import net.kankrittapon.rpgem.core.entity.custom.ZombieKing;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registers custom entity types.
 */
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE,
            RPGEMCore.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<ZombieKing>> ZOMBIE_KING = ENTITIES.register(
            "zombie_king",
            () -> EntityType.Builder.of(ZombieKing::new, MobCategory.MONSTER)
                    .sized(1.2f, 3.0f)
                    .build("zombie_king"));

    public static final DeferredHolder<EntityType<?>, EntityType<SkeletonLord>> SKELETON_LORD = ENTITIES.register(
            "skeleton_lord",
            () -> EntityType.Builder.of(SkeletonLord::new, MobCategory.MONSTER)
                    .sized(1.2f, 3.0f)
                    .build("skeleton_lord"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
