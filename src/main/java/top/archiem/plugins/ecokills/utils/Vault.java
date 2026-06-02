import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.OfflinePlayer;

public class Vault {
    private Economy econ;

    public void Vault(Economy econ){
        this.econ = econ;
    }

    public Economy getEconomy() {
        return econ;
    }

    public boolean payPlayer(OfflinePlayer player, double amount){
        EconomyResponse r = econ.depositPlayer(player, amount);
        if(r.transactionSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeMoney(OfflinePlayer player, double amount){
        EconomyResponse r = econ.withdrawPlayer(player, amount);
        if(r.transactionSuccess()) {
            return true;
        } else {
            return false;
        }
    }



    
}