package top.archiem.plugins.ecokills;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import top.archiem.plugins.ecokills.listeners.*;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;

public class EcoKills extends JavaPlugin {

    private Economy econ;
    FileConfiguration config = getConfig();

    private boolean flatFee;
    private double feeAmount;
    private int percentage;

    private String killerMessage;
    private String victimMessage;


    @Override
    public void onEnable() {
        getLogger().info("Enabling EcoKills...");
        if(config.getBoolean("ascii-banner")){
            getLogger().info("""
                ███████╗ ██████╗ ██████╗ ██╗  ██╗██╗██╗     ██╗     ███████╗
                ██╔════╝██╔════╝██╔═══██╗██║ ██╔╝██║██║     ██║     ██╔════╝
                █████╗  ██║     ██║   ██║█████╔╝ ██║██║     ██║     ███████╗
                ██╔══╝  ██║     ██║   ██║██╔═██╗ ██║██║     ██║     ╚════██║
                ███████╗╚██████╗╚██████╔╝██║  ██╗██║███████╗███████╗███████║
                ╚══════╝ ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚══════╝                                                                                                               
                """);
        }
        if (!setupEconomy()) {
            getLogger().severe("Vault dependency not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("Loading configuration...");
        saveDefaultConfig();
        flatFee = config.getBoolean("payments.flat-fee");
        feeAmount = config.getDouble("payments.fee-amount");
        percentage = config.getInt("payments.percentage");
        killerMessage = config.getString("messages.killer");
        victimMessage = config.getString("messages.victim");

        if(percentage < 1 || percentage > 100){
            getLogger().severe("Invalid percentage value in config! Must be between 1 and 100. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        
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

    public Economy getEconomy() {
        return econ;
    }

    public boolean isFlatFee() {
        return flatFee;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getKillerMessage() {
        return killerMessage;
    }

    public String getVictimMessage() {
        return victimMessage;
    }
    


}    