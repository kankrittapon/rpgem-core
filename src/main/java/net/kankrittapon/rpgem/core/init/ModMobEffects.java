package net.kankrittapon.rpgem.core.init;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT,
            RPGEMCore.MODID);

    // Fate Seal: Forbids healing and makes undying (handled in events)
    public static final DeferredHolder<MobEffect, MobEffect> FATE_SEAL = MOB_EFFECTS.register("fate_seal",
            () -> new MobEffect(MobEffectCategory.HARMFUL, 0x4A0000) {
            });

    // Boundless Grace: God Mode (Savior Mechanic)
    public static final DeferredHolder<MobEffect, MobEffect> BOUNDLESS_GRACE = MOB_EFFECTS.register("boundless_grace",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFFD700) {
            });

    // Juggernaut: +Max HP
    public static final DeferredHolder<MobEffect, MobEffect> JUGGERNAUT = MOB_EFFECTS.register("juggernaut",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x8B0000) {
            });

    // Iron Thorns: Reflect Damage
    public static final DeferredHolder<MobEffect, MobEffect> IRON_THORNS = MOB_EFFECTS.register("iron_thorns",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x808080) {
            });

    // Evasion: Dodge Chance
    public static final DeferredHolder<MobEffect, MobEffect> EVASION = MOB_EFFECTS.register("evasion",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x00FFFF) {
            });

    // Unstoppable: Knockback Resistance
    public static final DeferredHolder<MobEffect, MobEffect> UNSTOPPABLE = MOB_EFFECTS.register("unstoppable",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFFA500) {
            });

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
