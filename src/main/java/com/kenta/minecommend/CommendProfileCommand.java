package com.kenta.minecommend;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommendProfileCommand implements CommandExecutor {
    private final DataManager dataManager;

    public CommendProfileCommand(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].isBlank()) {
            sender.sendMessage("Please enter a player name: /commendprofile <player>");
            return true;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        if (!target.isOnline() && !target.hasPlayedBefore()) {
            sender.sendMessage("Player not found.");
            return true;
        }

        int friendly = dataManager.getCommendCount(target.getUniqueId(), "Friendly");
        int teamwork = dataManager.getCommendCount(target.getUniqueId(), "Teamwork");
        int helpful = dataManager.getCommendCount(target.getUniqueId(), "Helpful");

        sender.sendMessage("Friendly: " + friendly);
        sender.sendMessage("Teamwork: " + teamwork);
        sender.sendMessage("Helpful: " + helpful);
        sender.sendMessage("Total Commend: " + (friendly + teamwork + helpful));
        return true;
    }
}
