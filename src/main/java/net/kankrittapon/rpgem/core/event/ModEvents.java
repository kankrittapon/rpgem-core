package net.kankrittapon.rpgem.core.event;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.config.Config;
import net.kankrittapon.rpgem.core.init.ModAttributes;
import net.kankrittapon.rpgem.core.init.ModDataComponents;
import net.kankrittapon.rpgem.core.init.ModMobEffects;
import net.kankrittapon.rpgem.core.threshold.TraitThresholdLoader;
import net.kankrittapon.rpgem.core.threshold.TraitTier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Random;

import net.minecraft.world.entity.player.Player;

@EventBusSubscriber(modid = RPGEMCore.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    private static final Random RANDOM = new Random();

    /**
     * Helper: อ่าน attribute อย่างปลอดภัย - คืนค่า defaultValue ถ้า entity ไม่มี
     * attribute นั้น
     * ป้องกัน IllegalArgumentException จาก Villager/Animals ที่ไม่ได้ register
     * custom attributes
     */
    private static double getSafeAttributeValue(LivingEntity entity, Holder<Attribute> attribute, double defaultValue) {
        AttributeInstance inst = entity.getAttributes().getInstance(attribute);
        return inst != null ? inst.getValue() : defaultValue;
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity target = event.getEntity();
        DamageSource source = event.getSource();

        // 1. Evasion (Dodge) Logic
        double evasion = getSafeAttributeValue(target, ModAttributes.EVASION, 0.0);
        double dodgeChance = Config.DODGE_CHANCE.get() + evasion;

        if (RANDOM.nextDouble() < dodgeChance) {
            event.setCanceled(true);
            if (target instanceof Player player) {
                player.displayClientMessage(
                        Component.translatable("chat.rpgem_core.dodged").withStyle(ChatFormatting.AQUA), true);
            }
            return;
        }

        // 2. Iron Thorns (Reflect) Logic
        if (target.hasEffect(ModMobEffects.IRON_THORNS)) {
            if (source.getDirectEntity() instanceof LivingEntity attacker) {
                double reflectChance = getSafeAttributeValue(target, ModAttributes.REFLECT_CHANCE, 0.0)
                        + Config.THORNS_CHANCE.get();
                if (RANDOM.nextDouble() < reflectChance) {
                    double reflectedDamage = event.getAmount() * Config.REFLECTION_MULTIPLIER.get();
                    attacker.hurt(target.damageSources().thorns(target), (float) reflectedDamage);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        LivingEntity target = event.getEntity();
        if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            float amount = event.getNewDamage();

            // 3. Critical Hit Logic
            double critChance = getSafeAttributeValue(attacker, ModAttributes.CRIT_CHANCE, 0.0);
            if (RANDOM.nextDouble() < critChance) {
                amount *= 1.5f;
                attacker.sendSystemMessage(
                        Component.translatable("chat.rpgem_core.critical").withStyle(ChatFormatting.GOLD));
            }

            // 4. Armor Penetration
            double armorPen = getSafeAttributeValue(attacker, ModAttributes.ARMOR_PENETRATION, 0.0);
            if (armorPen > 0) {
                float penDamage = (float) Math.min(amount * armorPen,
                        Config.ARMOR_PENETRATION_CAP != null ? Config.ARMOR_PENETRATION_CAP.get() : 1000.0);
                target.setHealth(target.getHealth() - penDamage);
            }

            // 5. Life Steal Logic
            double lifeSteal = getSafeAttributeValue(attacker, ModAttributes.LIFE_STEAL, 0.0);
            if (lifeSteal > 0) {
                attacker.heal((float) (amount * lifeSteal));
            }

            event.setNewDamage(amount);
        }
    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        if (event.getEntity().hasEffect(ModMobEffects.FATE_SEAL)) {
            event.setCanceled(true);
        }
    }

    /**
     * Apply Threshold-based Trait bonuses ตาม upgradeLevel ที่อ่านจาก JSON
     *
     * ไม่มี hardcode: เพิ่ม/แก้ Trait ได้ที่ไฟล์ JSON:
     * data/rpgem_core/thresholds/weapon_thresholds.json
     * data/rpgem_core/thresholds/armor_reduction_thresholds.json
     * data/rpgem_core/thresholds/armor_evasion_thresholds.json
     */
    @SubscribeEvent
    public static void onItemAttributeModifier(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        int upgradeLevel = stack.getOrDefault(ModDataComponents.UPGRADE_LEVEL, 0);
        if (upgradeLevel <= 0)
            return;

        if (stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem) {
            // ===== Weapon Path =====
            TraitTier tier = TraitThresholdLoader.getWeaponTier(upgradeLevel);
            if (tier == null)
                return;

            if (tier.attackDamagePerLevel > 0) {
                addModifier(event, Attributes.ATTACK_DAMAGE,
                        "upgrade_damage", upgradeLevel * tier.attackDamagePerLevel,
                        AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND);
            }
            if (tier.lifeSteal > 0) {
                addModifier(event, ModAttributes.LIFE_STEAL,
                        "upgrade_lifesteal", tier.lifeSteal,
                        AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND);
            }
            if (tier.critChance > 0) {
                addModifier(event, ModAttributes.CRIT_CHANCE,
                        "upgrade_crit", tier.critChance,
                        AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND);
            }
            if (tier.elementDamage > 0) {
                addModifier(event, ModAttributes.ELEMENT_DAMAGE,
                        "upgrade_element", tier.elementDamage,
                        AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND);
            }

        } else if (stack.getItem() instanceof ArmorItem armorItem) {
            // ===== Armor Path =====
            EquipmentSlot slot = armorItem.getEquipmentSlot();
            EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);
            String path = stack.getOrDefault(ModDataComponents.ARMOR_PATH, "none");

            TraitTier tier;
            if (path.equals("reduction")) {
                tier = TraitThresholdLoader.getArmorReductionTier(upgradeLevel);
            } else if (path.equals("evasion")) {
                tier = TraitThresholdLoader.getArmorEvasionTier(upgradeLevel);
            } else {
                return;
            }
            if (tier == null)
                return;

            if (tier.armorDefensePerLevel > 0) {
                addModifier(event, Attributes.ARMOR,
                        "upgrade_armor", upgradeLevel * tier.armorDefensePerLevel,
                        AttributeModifier.Operation.ADD_VALUE, slotGroup);
            }
            if (tier.damageReduction > 0) {
                addModifier(event, ModAttributes.DAMAGE_REDUCTION,
                        "upgrade_reduction", tier.damageReduction,
                        AttributeModifier.Operation.ADD_VALUE, slotGroup);
            }
            if (tier.evasion > 0) {
                addModifier(event, ModAttributes.EVASION,
                        "upgrade_evasion", tier.evasion,
                        AttributeModifier.Operation.ADD_VALUE, slotGroup);
            }
            if (tier.maxHpPerLevel > 0) {
                addModifier(event, Attributes.MAX_HEALTH,
                        "upgrade_hp", upgradeLevel * tier.maxHpPerLevel,
                        AttributeModifier.Operation.ADD_VALUE, slotGroup);
            }
            if (tier.reflectResist > 0) {
                addModifier(event, ModAttributes.REFLECT_RESIST,
                        "upgrade_reflect_resist", tier.reflectResist,
                        AttributeModifier.Operation.ADD_VALUE, slotGroup);
            }
            if (tier.sealResist > 0) {
                addModifier(event, ModAttributes.SEAL_RESIST,
                        "upgrade_seal_resist", tier.sealResist,
                        AttributeModifier.Operation.ADD_VALUE, slotGroup);
            }
        }
    }

    /**
     * Helper: เพิ่ม AttributeModifier โดยใช้ ResourceLocation จาก mod namespace
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void addModifier(
            ItemAttributeModifierEvent event,
            net.minecraft.core.Holder attribute,
            String key,
            double value,
            AttributeModifier.Operation operation,
            EquipmentSlotGroup slotGroup) {
        event.addModifier(attribute,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(RPGEMCore.MODID, key),
                        value, operation),
                slotGroup);
    }
}
