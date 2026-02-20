package net.kankrittapon.rpgem.core.event;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.init.ModAttributes;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber(modid = RPGEMCore.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    /**
     * Attach custom RPG attributes to Player entity.
     */
    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        // Weapon attributes
        event.add(EntityType.PLAYER, ModAttributes.LIFE_STEAL);
        event.add(EntityType.PLAYER, ModAttributes.CRIT_CHANCE);
        event.add(EntityType.PLAYER, ModAttributes.ELEMENT_DAMAGE);
        event.add(EntityType.PLAYER, ModAttributes.ACCURACY);
        event.add(EntityType.PLAYER, ModAttributes.ARMOR_PENETRATION);
        event.add(EntityType.PLAYER, ModAttributes.ANTI_HEAL);

        // Armor attributes
        event.add(EntityType.PLAYER, ModAttributes.DAMAGE_REDUCTION);
        event.add(EntityType.PLAYER, ModAttributes.EVASION);
        event.add(EntityType.PLAYER, ModAttributes.REFLECT_RESIST);
        event.add(EntityType.PLAYER, ModAttributes.SEAL_RESIST);
        event.add(EntityType.PLAYER, ModAttributes.REFLECT_CHANCE);
    }
}
