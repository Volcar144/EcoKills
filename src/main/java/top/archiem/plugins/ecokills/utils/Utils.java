package top.archiem.plugins.ecokills.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import top.archiem.plugins.ecokills.EcoKills;

public class Utils {

  public static Component colorize(String msg) {
    if (msg.contains("<") && msg.contains(">")) {
      return MiniMessage.miniMessage().deserialize(msg);
    }
    return LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
  }

  public static String legacyColorize(String msg) {
    return LegacyComponentSerializer.legacyAmpersand()
        .serialize(LegacyComponentSerializer.legacyAmpersand().deserialize(msg));
  }

  public static String replaceText(String msg, String toReplace, String replacement) {
    return msg.replace(toReplace, replacement);
  }

  public static Component addPlaceholders(
      String msg, Player player, boolean isKiller, double amount, Economy econ) {
    String placeholder = isKiller ? "%killer%" : "%victim%";
    String replacedMsg = replaceText(msg, placeholder, player.getName());
    String finalMsg = replaceText(replacedMsg, "%amount%", econ.format(amount));
    if (EcoKills.getPlugin(EcoKills.class).isPapiEnabled()) {
      finalMsg = PlaceholderAPI.setPlaceholders(player, finalMsg);
    }
    return colorize(finalMsg);
  }
}
