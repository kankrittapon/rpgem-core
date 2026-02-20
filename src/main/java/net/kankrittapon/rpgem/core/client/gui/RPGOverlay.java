package net.kankrittapon.rpgem.core.client.gui;

import net.kankrittapon.rpgem.core.RPGEMCore;
import net.kankrittapon.rpgem.core.init.ModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = RPGEMCore.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class RPGOverlay {

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || mc.player == null)
            return;

        renderStats(event.getGuiGraphics(), mc.player);
    }

    private static void renderStats(GuiGraphics guiGraphics, Player player) {
        int x = guiGraphics.guiWidth() - 100;
        int y = guiGraphics.guiHeight() - 120;
        int spacing = 10;

        List<Component> stats = new ArrayList<>();

        double dodge = player.getAttributeValue(ModAttributes.EVASION);
        if (dodge > 0)
            stats.add(Component.translatable("gui.rpgem_core.dodge").append(": " + (int) (dodge * 100) + "%"));

        double crit = player.getAttributeValue(ModAttributes.CRIT_CHANCE);
        if (crit > 0)
            stats.add(Component.translatable("gui.rpgem_core.crit").append(": " + (int) (crit * 100) + "%"));

        double lifeSteal = player.getAttributeValue(ModAttributes.LIFE_STEAL);
        if (lifeSteal > 0)
            stats.add(Component.translatable("gui.rpgem_core.life_steal").append(": " + (int) (lifeSteal * 100) + "%"));

        double armorPen = player.getAttributeValue(ModAttributes.ARMOR_PENETRATION);
        if (armorPen > 0)
            stats.add(Component.translatable("gui.rpgem_core.armor_pen").append(": " + (int) (armorPen * 100) + "%"));

        for (int i = 0; i < stats.size(); i++) {
            guiGraphics.drawString(Minecraft.getInstance().font, stats.get(i), x, y + (i * spacing), 0xFFFFFF);
        }
    }
}
