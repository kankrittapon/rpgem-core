package net.kankrittapon.rpgem.core.threshold;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.kankrittapon.rpgem.core.RPGEMCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TraitThresholdLoader — โหลดและ Cache ตาราง Threshold Trait จากไฟล์ JSON
 *
 * ไฟล์ JSON อยู่ที่: /data/rpgem_core/thresholds/*.json
 * ผู้ใช้สามารถ Override ด้วย Datapack ได้ในอนาคต
 *
 * ใช้งาน:
 * TraitThresholdLoader.getWeaponTier(upgradeLevel)
 * TraitThresholdLoader.getArmorReductionTier(upgradeLevel)
 * TraitThresholdLoader.getArmorEvasionTier(upgradeLevel)
 */
public class TraitThresholdLoader {

    private static final Logger LOGGER = LogManager.getLogger(RPGEMCore.MODID);
    private static final Gson GSON = new Gson();

    private static final String BASE_PATH = "/data/rpgem_core/thresholds/";

    // Cached tier lists
    private static List<TraitTier> weaponTiers = null;
    private static List<TraitTier> armorReductionTiers = null;
    private static List<TraitTier> armorEvasionTiers = null;

    /**
     * โหลด Tier list ทั้งหมดจาก JSON
     * เรียกครั้งเดียวตอน Mod Load หรือ Reload
     */
    public static void loadAll() {
        weaponTiers = loadFile("weapon_thresholds.json");
        armorReductionTiers = loadFile("armor_reduction_thresholds.json");
        armorEvasionTiers = loadFile("armor_evasion_thresholds.json");
        LOGGER.info("[RPGem] Loaded {} weapon tiers, {} armor-reduction tiers, {} armor-evasion tiers",
                weaponTiers.size(), armorReductionTiers.size(), armorEvasionTiers.size());
    }

    /**
     * หา TraitTier ของอาวุธตาม upgradeLevel
     * 
     * @return TraitTier ที่ตรงกัน หรือ null ถ้าไม่มี
     */
    public static TraitTier getWeaponTier(int upgradeLevel) {
        return findMatchingTier(getWeaponTiers(), upgradeLevel);
    }

    /**
     * หา TraitTier ของเกราะสาย Reduction ตาม upgradeLevel
     */
    public static TraitTier getArmorReductionTier(int upgradeLevel) {
        return findMatchingTier(getArmorReductionTiers(), upgradeLevel);
    }

    /**
     * หา TraitTier ของเกราะสาย Evasion ตาม upgradeLevel
     */
    public static TraitTier getArmorEvasionTier(int upgradeLevel) {
        return findMatchingTier(getArmorEvasionTiers(), upgradeLevel);
    }

    // ===== Internal Helpers =====

    private static TraitTier findMatchingTier(List<TraitTier> tiers, int upgradeLevel) {
        for (TraitTier tier : tiers) {
            if (tier.matches(upgradeLevel)) {
                return tier;
            }
        }
        return null;
    }

    private static List<TraitTier> getWeaponTiers() {
        if (weaponTiers == null)
            loadAll();
        return weaponTiers;
    }

    private static List<TraitTier> getArmorReductionTiers() {
        if (armorReductionTiers == null)
            loadAll();
        return armorReductionTiers;
    }

    private static List<TraitTier> getArmorEvasionTiers() {
        if (armorEvasionTiers == null)
            loadAll();
        return armorEvasionTiers;
    }

    private static List<TraitTier> loadFile(String filename) {
        String fullPath = BASE_PATH + filename;
        try (InputStream stream = TraitThresholdLoader.class.getResourceAsStream(fullPath)) {
            if (stream == null) {
                LOGGER.error("[RPGem] ไม่พบไฟล์ threshold: {}", fullPath);
                return Collections.emptyList();
            }
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            Type listType = new TypeToken<ArrayList<TraitTier>>() {
            }.getType();
            List<TraitTier> result = GSON.fromJson(reader, listType);
            return result != null ? result : Collections.emptyList();
        } catch (Exception e) {
            LOGGER.error("[RPGem] โหลด {} ล้มเหลว: {}", fullPath, e.getMessage());
            return Collections.emptyList();
        }
    }
}
