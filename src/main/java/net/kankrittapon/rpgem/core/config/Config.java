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

    public static final ModConfigSpec SPEC = BUILDER.build();
}
