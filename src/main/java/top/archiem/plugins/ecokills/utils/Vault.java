package top.archiem.plugins.ecokills.utils;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

public class Vault {
  private Economy econ;

  public Vault(Economy econ) {
    this.econ = econ;
  }

  public Economy getEconomy() {
    return econ;
  }

  public boolean payPlayer(OfflinePlayer player, double amount) {
    EconomyResponse r = econ.depositPlayer(player, amount);
    if (r.transactionSuccess()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean removeMoney(OfflinePlayer player, double amount) {
    EconomyResponse r = econ.withdrawPlayer(player, amount);
    if (r.transactionSuccess()) {
      return true;
    } else {
      return false;
    }
  }

  public double getBalance(OfflinePlayer player) {
    return econ.getBalance(player);
  }
}
