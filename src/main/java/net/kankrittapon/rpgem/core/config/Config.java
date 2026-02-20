package net.kankrittapon.rpgem.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        // ==========================================
        // RPG Core Settings
        // ==========================================
        public static final ModConfigSpec.DoubleValue DODGE_CHANCE = BUILDER
                        .comment("Base chance to dodge an attack (0.0 - 1.0). Default: 0.3 (30%)")
                        .defineInRange("dodgeChance", 0.3, 0.0, 1.0);

        public static final ModConfigSpec.DoubleValue ARMOR_PENETRATION_CAP = BUILDER
                        .comment("Max damage dealt by Armor Penetration per hit. Default: 1000.0")
                        .defineInRange("armorPenetrationCap", 1000.0, 0.0, 1000000.0);

        public static final ModConfigSpec.DoubleValue FATE_SEAL_CHANCE = BUILDER
                        .comment("Chance for Fate Seal to trigger when conditions are met (0.0 - 1.0). Default: 0.5 (50%)")
                        .defineInRange("fateSealChance", 0.5, 0.0, 1.0);

        public static final ModConfigSpec.DoubleValue FATE_SEAL_THRESHOLD = BUILDER
                        .comment("HP threshold ratio for Fate Seal to trigger (0.0 - 1.0). Default: 0.15 (15%)")
                        .defineInRange("fateSealThreshold", 0.15, 0.0, 1.0);

        public static final ModConfigSpec.DoubleValue REFLECTION_MULTIPLIER = BUILDER
                        .comment("Multiplier for reflected damage. Default: 0.5")
                        .defineInRange("reflectionMultiplier", 0.5, 0.0, 10.0);

        public static final ModConfigSpec.DoubleValue THORNS_CHANCE = BUILDER
                        .comment("Base chance for Iron Thorns to reflect damage (0.0 - 1.0). Default: 0.3 (30%)")
                        .defineInRange("thornsChance", 0.3, 0.0, 1.0);

        // ==========================================
        // Forging & Scaling Settings
        // ==========================================
        public static final ModConfigSpec.DoubleValue WEAPON_DAMAGE_BONUS_PER_LEVEL = BUILDER
                        .comment("Flat attack damage bonus per upgrade level. Default: 2.0")
                        .defineInRange("weaponDamageBonusPerLevel", 2.0, 0.0, 100.0);

        public static final ModConfigSpec.DoubleValue WEAPON_CRIT_BONUS_PER_LEVEL = BUILDER
                        .comment("Critical chance bonus per upgrade level (0.01 = 1%). Default: 0.02 (2%)")
                        .defineInRange("weaponCritBonusPerLevel", 0.02, 0.0, 1.0);

        public static final ModConfigSpec.DoubleValue ARMOR_DEFENSE_BONUS_PER_LEVEL = BUILDER
                        .comment("Flat armor defense bonus per upgrade level. Default: 1.0")
                        .defineInRange("armorDefenseBonusPerLevel", 1.0, 0.0, 50.0);

        public static final ModConfigSpec.DoubleValue ARMOR_REDUCTION_EVASION_BONUS_PER_LEVEL = BUILDER
                        .comment("Evasion or Damage Reduction bonus per level based on armor path (0.01 = 1%). Default: 0.015 (1.5%)")
                        .defineInRange("armorReductionEvasionBonusPerLevel", 0.015, 0.0, 0.5);

        public static final ModConfigSpec SPEC = BUILDER.build();
}
