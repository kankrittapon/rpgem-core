package net.kankrittapon.rpgem.core.threshold;

/**
 * TraitTier — ข้อมูลของแต่ละช่วงเลเวล (Threshold Tier)
 * โหลดจาก JSON ผ่าน TraitThresholdLoader
 *
 * ทุก field เป็น double, ค่า 0.0 = ไม่มี bonus สำหรับ trait นั้น
 */
public class TraitTier {

    /** ระดับขั้นต่ำของช่วงนี้ (inclusive) */
    public int minLevel = 0;

    /** ระดับสูงสุดของช่วงนี้ (inclusive, -1 = ไม่มีขีดสูงสุด) */
    public int maxLevel = -1;

    // ===== Weapon Traits =====
    /** โบนัส Attack Damage ต่อเลเวล (stackable) */
    public double attackDamagePerLevel = 0.0;

    /** Life Steal (0.0 - 1.0, เช่น 0.02 = 2%) */
    public double lifeSteal = 0.0;

    /** Critical Chance (0.0 - 1.0) */
    public double critChance = 0.0;

    /** Element Damage (0.0 - 1.0) */
    public double elementDamage = 0.0;

    // ===== Armor Traits =====
    /** Damage Reduction (0.0 - 1.0) */
    public double damageReduction = 0.0;

    /** Evasion (0.0 - 1.0) */
    public double evasion = 0.0;

    /** Max HP Bonus ต่อเลเวล */
    public double maxHpPerLevel = 0.0;

    /** Reflect Resist (0.0 - 1.0) */
    public double reflectResist = 0.0;

    /** Seal Resist (0.0 - 1.0) */
    public double sealResist = 0.0;

    /** Vanilla Armor Defense Bonus (absolute, stackable with base) */
    public double armorDefensePerLevel = 0.0;

    /**
     * ตรวจสอบว่า upgradeLevel เข้าข่าย tier นี้หรือไม่
     */
    public boolean matches(int upgradeLevel) {
        return upgradeLevel >= minLevel && (maxLevel < 0 || upgradeLevel <= maxLevel);
    }
}
