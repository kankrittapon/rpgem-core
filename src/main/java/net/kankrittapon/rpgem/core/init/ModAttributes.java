package net.kankrittapon.rpgem.core.init;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Custom Attributes for RPGEM Core.
 */
public class ModAttributes {
        public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE,
                        RPGEMCore.MODID);

        // === Weapon Attributes ===
        public static final Holder<Attribute> LIFE_STEAL = ATTRIBUTES.register("life_steal",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".life_steal", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> CRIT_CHANCE = ATTRIBUTES.register("crit_chance",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".crit_chance", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> ELEMENT_DAMAGE = ATTRIBUTES.register("element_damage",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".element_damage", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> ACCURACY = ATTRIBUTES.register("accuracy",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".accuracy", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> ARMOR_PENETRATION = ATTRIBUTES.register("armor_penetration",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".armor_penetration", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> ANTI_HEAL = ATTRIBUTES.register("anti_heal",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".anti_heal", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        // === Armor Attributes ===
        public static final Holder<Attribute> DAMAGE_REDUCTION = ATTRIBUTES.register("damage_reduction",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".damage_reduction", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> EVASION = ATTRIBUTES.register("evasion",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".evasion", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> REFLECT_RESIST = ATTRIBUTES.register("reflect_resist",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".reflect_resist", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> SEAL_RESIST = ATTRIBUTES.register("seal_resist",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".seal_resist", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static final Holder<Attribute> REFLECT_CHANCE = ATTRIBUTES.register("reflect_chance",
                        () -> new RangedAttribute("attribute." + RPGEMCore.MODID + ".reflect_chance", 0.0, 0.0, 1.0)
                                        .setSyncable(true));

        public static void register(IEventBus eventBus) {
                ATTRIBUTES.register(eventBus);
        }
}
