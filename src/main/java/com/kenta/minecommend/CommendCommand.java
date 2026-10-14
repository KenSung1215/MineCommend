package com.kenta.minecommend;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;


public class CommendCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Please enter a player name.");
            return true;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            player.sendMessage("Player not found.");
            return true;
        }

        if (player.getUniqueId().equals(target.getUniqueId())) {
            player.sendMessage("You cannot commend yourself!");
            return true;
        }

        Inventory gui = Bukkit.createInventory(null, 27, "Commend " + target.getName());

        gui.setItem(11, createMenuItem(Material.RED_DYE, "Friendly"));

        gui.setItem(13, createMenuItem(Material.IRON_SWORD, "Teamwork"));

        gui.setItem(15, createMenuItem(Material.RED_BED, "Helpful"));

        player.openInventory(gui);

        return true;
    }

    private ItemStack createMenuItem(Material material, String name){

        ItemStack newItem = new ItemStack(material);
        ItemMeta newMeta = newItem.getItemMeta();
        newMeta.displayName(Component.text(name));
        newItem.setItemMeta(newMeta);

        return newItem;
    }


}
