package top.archiem.plugins.ecokills.listeners;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.kyori.adventure.text.Component;
import net.milkbowl.vault.economy.Economy;
import top.archiem.plugins.ecokills.EcoKills;
import top.archiem.plugins.ecokills.utils.Utils;
import top.archiem.plugins.ecokills.utils.Vault;

public class PlayerDeathListener implements Listener {
    
    private Economy econ = EcoKills.getPlugin(EcoKills.class).getEconomy();

    private boolean flatFee = EcoKills.getPlugin(EcoKills.class).isFlatFee();
    private double feeAmount = EcoKills.getPlugin(EcoKills.class).getFeeAmount();
    private int percentage = EcoKills.getPlugin(EcoKills.class).getPercentage();

    private String killerMessage = EcoKills.getPlugin(EcoKills.class).getKillerMessage();
    private String victimMessage = EcoKills.getPlugin(EcoKills.class).getVictimMessage();
    private EcoKills plugin = EcoKills.getPlugin(EcoKills.class);

    Vault vault = new Vault(econ);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();
        //Get the offline player for vault use
        OfflinePlayer offlineVictim = Bukkit.getOfflinePlayer(victim.getUniqueId());

        Player killer = victim.getKiller();
        OfflinePlayer offlineKiller;

        if(killer != null){
            offlineKiller = Bukkit.getOfflinePlayer(killer.getUniqueId());

            //Stop Suicide granting rewards
            if(killer.getUniqueId().equals(victim.getUniqueId())){
                return;
            }

            double victimBalence = vault.getBalance(offlineVictim);

            double rewardPercentage = percentage / 100;

            double rewardAmount;

            if(flatFee) {
                rewardAmount = feeAmount;
                if(victimBalence < rewardAmount) {
                    rewardAmount = victimBalence;
                }
            } else {
                rewardAmount = victimBalence * rewardPercentage;
            }

            if(rewardAmount < 0) {
                plugin.getLogger().severe("How the fuf did you even do this? The reward amount is less than 0! Please report this.");
                rewardAmount = 0;
            }

            boolean withdraw = vault.removeMoney(offlineVictim, rewardAmount);
            if(!withdraw) {
                plugin.getLogger().severe("Failed to remove money from victim's account!");
                return;
            }
            boolean pay = vault.payPlayer(offlineKiller, rewardAmount);
            if(!pay) {
                plugin.getLogger().severe("Failed to pay killer!");
                return;
            }

            Component killerMsg = Utils.addPlaceholders(killerMessage, killer, true, rewardAmount, econ);
            Component victimMsg = Utils.addPlaceholders(victimMessage, victim, false, rewardAmount, econ);

            victim.sendMessage(victimMsg);
            killer.sendMessage(killerMsg);

        } else {
            return;
        }

        

    }
}
