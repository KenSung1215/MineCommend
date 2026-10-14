package com.kenta.minecommend;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DataManager {
    private final JavaPlugin plugin;
    private final File file;
    private final FileConfiguration data;

    public DataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getDataFolder().mkdirs();
        this.file = new File(plugin.getDataFolder(), "data.yml");
        this.data = YamlConfiguration.loadConfiguration(file);
    }

    public int getCommendCount(UUID playerId, String type) {
        return data.getInt("players." + playerId + "." + type, 0);
    }

    public long getLastCommend(UUID senderId, UUID targetId) {
        return data.getLong("cooldowns." + senderId + "." + targetId, 0L);
    }

    public boolean addCommend(UUID senderId, UUID targetId, String type, long timestamp) {
        String countPath = "players." + targetId + "." + type;
        String cooldownPath = "cooldowns." + senderId + "." + targetId;
        Object previousCount = data.get(countPath);
        Object previousCooldown = data.get(cooldownPath);

        data.set(countPath, data.getInt(countPath, 0) + 1);
        data.set(cooldownPath, timestamp);
        try {
            data.save(file);
            return true;
        } catch (IOException e) {
            data.set(countPath, previousCount);
            data.set(cooldownPath, previousCooldown);
            plugin.getLogger().severe("Failed to save data.yml: " + e.getMessage());
            return false;
        }
    }
}
