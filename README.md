# RPGEM Core

**RPGEM Core** is the foundational library for the RPGEM modification suite. It provides shared resources, attributes, effects, and utilities that are required by other modules (`rpgem-alchemy-plus`, `rpgem-fairy-plus`, `rpgem-forging-plus`).

## 📚 Purpose (วัตถุประสงค์)
This module acts as a **Shared Dependency**. It does not add gameplay features on its own but ensures compatibility and standardization across the entire mod ecosystem.
(โมดูลนี้ทำหน้าที่เป็น **Library กลาง** ไม่ได้เพิ่มระบบเกมเพลย์โดยตรง แต่ช่วยให้ทุกโมดูลทำงานร่วมกันได้และมีมาตรฐานเดียวกัน)

## 🛠 Features (ฟีเจอร์หลัก)
1.  **Shared Mob Effects:**
    *   Registry of status effects used across multiple modules (e.g., `Boundless Grace`, `Juggernaut`).
    *   Prevents ID conflicts and ensures effects stack correctly.
2.  **Base Attributes:**
    *   Common attributes for RPG elements (Mana, Stamina - *Planned*).
3.  **Utility Classes:**
    *   Helper functions for math, NBT data handling, and network packets.

## 📦 Dependency Structure (โครงสร้างการพึ่งพา)
Other modules **must** include this mod to function:
*   `rpgem-alchemy-plus` depends on `rpgem-core`
*   `rpgem-fairy-plus` depends on `rpgem-core`
*   `rpgem-forging-plus` depends on `rpgem-core`

## 💡 Developer Note: When to Update Core?
**Q: Do I need to update Core every time I change another module?**
**A: No.** You only update Core when:
1.  **Adding a NEW Shared Effect/Attribute:** If you create a new potion effect (e.g., "Bleeding") that both *Alchemy* and *Forging* weapons might use.
2.  **Refactoring Common Logic:** If you change how NBT data is saved globally.

**Example 1:** Adding a new *Sword* in `rpgem-forging-plus`.
-> **Update Only:** `rpgem-forging-plus`. Core is untouched.

**Example 2:** Adding a new *Potion* in `rpgem-alchemy-plus`.
-> **Update Only:** `rpgem-alchemy-plus`. Core is untouched.

**Example 3:** Adding a new *Status Effect* "Freezing" that a sword applies AND a potion cures.
-> **Update:** `rpgem-core` (Add Effect) -> Then update both modules to use it.
