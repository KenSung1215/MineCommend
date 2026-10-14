package com.kenta.minecommend;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class CommendMenuListener implements Listener {
    private final CommendManager commendManager;

    public CommendMenuListener(CommendManager commendManager) {
        this.commendManager = commendManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().startsWith("Commend ")) {
            return;
        }

        int rawSlot = event.getRawSlot();
        int topSize = event.getView().getTopInventory().getSize();

        if (rawSlot < topSize
                || event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY
                || event.getAction() == InventoryAction.COLLECT_TO_CURSOR
                || event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD) {
            event.setCancelled(true);
        }

        if (rawSlot < 0 || rawSlot >= topSize) {
            return;
        }

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        String type = switch (rawSlot) {
            case 11 -> "Friendly";
            case 13 -> "Teamwork";
            case 15 -> "Helpful";
            default -> null;
        };
        if (type == null) {
            return;
        }

        String targetName = event.getView().getTitle().substring("Commend ".length());
        Player target = Bukkit.getPlayerExact(targetName);
        if (target == null) {
            player.sendMessage("Player not found.");
            return;
        }

        switch (commendManager.commend(player, target, type)) {
            case SUCCESS -> player.sendMessage("You selected " + type + ".");
            case COOLDOWN -> player.sendMessage("You cannot commend this player again within 24 hours.");
            case SAVE_FAILED -> player.sendMessage("Failed to save commend.");
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!event.getView().getTitle().startsWith("Commend ")) {
            return;
        }

        int topSize = event.getView().getTopInventory().getSize();
        if (event.getRawSlots().stream().anyMatch(slot -> slot < topSize)) {
            event.setCancelled(true);
        }
    }
}
