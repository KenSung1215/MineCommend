package com.kenta.minecommend;

import org.bukkit.entity.Player;

public class CommendManager {
    private static final long COOLDOWN_MILLIS = 24L * 60 * 60 * 1000;
    private final DataManager dataManager;

    public enum Result {
        SUCCESS, COOLDOWN, SAVE_FAILED
    }

    public CommendManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Result commend(Player sender, Player target, String type) {
        if (!type.equals("Friendly") && !type.equals("Teamwork") && !type.equals("Helpful")) {
            return Result.SAVE_FAILED;
        }

        long now = System.currentTimeMillis();
        long lastCommend = dataManager.getLastCommend(sender.getUniqueId(), target.getUniqueId());
        if (lastCommend > 0 && now - lastCommend < COOLDOWN_MILLIS) {
            return Result.COOLDOWN;
        }

        return dataManager.addCommend(sender.getUniqueId(), target.getUniqueId(), type, now)
                ? Result.SUCCESS : Result.SAVE_FAILED;
    }
}
