package top.archiem.plugins.ecokills;

import org.bukkit.plugin.java.JavaPlugin;
import top.archiem.plugins.ecokills.managers.PluginManager;
import top.archiem.plugins.ecokills.listeners.PlayerListener;

public class EcoKills extends JavaPlugin {

    private Economy econ;
    
    
    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe("Vault dependency not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        
        getLogger().info("EcoKills has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EcoKills has been disabled!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }



    
    
}