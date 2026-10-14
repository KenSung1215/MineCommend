package com.kenta.minecommend;

import org.bukkit.plugin.java.JavaPlugin;

public class MineCommend extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("MineCommend has been enabled!");

        getCommand("commend").setExecutor(new CommendCommand());

        DataManager dataManager = new DataManager(this);
        CommendManager commendManager = new CommendManager(dataManager);
        getCommand("commendprofile").setExecutor(new CommendProfileCommand(dataManager));

        getServer().getPluginManager().registerEvents(
                new CommendMenuListener(commendManager),
                this
        );
    }
}
