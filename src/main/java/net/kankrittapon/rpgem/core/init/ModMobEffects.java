package net.kankrittapon.rpgem.core.init;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class ModMobEffects {
        public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT,
                        RPGEMCore.MODID);

        // Fate Seal: Forbids healing and makes undying (handled in events)
        public static final DeferredHolder<MobEffect, MobEffect> FATE_SEAL = MOB_EFFECTS.register("fate_seal",
                        () -> new RPGEffect(MobEffectCategory.HARMFUL, 0x4A0000));

        // Boundless Grace: God Mode (Savior Mechanic)
        public static final DeferredHolder<MobEffect, MobEffect> BOUNDLESS_GRACE = MOB_EFFECTS.register(
                        "boundless_grace",
                        () -> new RPGEffect(MobEffectCategory.BENEFICIAL, 0xFFD700)
                                        .addAttributeModifier(ModAttributes.REFLECT_RESIST,
                                                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                                                        RPGEMCore.MODID, "grace_reflect_resist"),
                                                        1.0,
                                                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE)
                                        .addAttributeModifier(ModAttributes.SEAL_RESIST,
                                                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                                                        RPGEMCore.MODID, "grace_seal_resist"),
                                                        1.0,
                                                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE)
                                        .addAttributeModifier(ModAttributes.EVASION,
                                                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                                                        RPGEMCore.MODID, "grace_evasion"),
                                                        0.8,
                                                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE)
                                        .addAttributeModifier(ModAttributes.REFLECT_CHANCE,
                                                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                                                        RPGEMCore.MODID, "grace_reflect_chance"),
                                                        0.8,
                                                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE));

        // Juggernaut: +Max HP
        public static final DeferredHolder<MobEffect, MobEffect> JUGGERNAUT = MOB_EFFECTS.register("juggernaut",
                        () -> new RPGEffect(MobEffectCategory.BENEFICIAL, 0x8B0000)
                                        .addAttributeModifier(Attributes.MAX_HEALTH,
                                                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                                                        RPGEMCore.MODID, "juggernaut_health"),
                                                        4.0,
                                                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE));

        // Iron Thorns: Reflect Damage
        public static final DeferredHolder<MobEffect, MobEffect> IRON_THORNS = MOB_EFFECTS.register("iron_thorns",
                        () -> new RPGEffect(MobEffectCategory.BENEFICIAL, 0x808080));

        // Evasion: Dodge Chance
        public static final DeferredHolder<MobEffect, MobEffect> EVASION = MOB_EFFECTS.register("evasion",
                        () -> new RPGEffect(MobEffectCategory.BENEFICIAL, 0x00FFFF));

        // Unstoppable: Knockback Resistance
        public static final DeferredHolder<MobEffect, MobEffect> UNSTOPPABLE = MOB_EFFECTS.register("unstoppable",
                        () -> new RPGEffect(MobEffectCategory.BENEFICIAL, 0xFFA500)
                                        .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
                                                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                                                        RPGEMCore.MODID, "unstoppable_kb"),
                                                        1.0,
                                                        net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE));

        public static class RPGEffect extends MobEffect {
                protected RPGEffect(MobEffectCategory category, int color) {
                        super(category, color);
                }
        }

        public static void register(IEventBus eventBus) {
                MOB_EFFECTS.register(eventBus);
        }
}
