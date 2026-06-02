import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

public class Vault {
    private Economy econ;

    public void Vault(Economy econ){
        this.econ = econ;
    }

    public Economy getEconomy() {
        return econ;
    }

    
}