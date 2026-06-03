package top.archiem.plugins.ecokills.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.Commands;
import top.archiem.plugins.ecokills.EcoKills;
import top.archiem.plugins.ecokills.utils.Utils;

public class MainCommand {

  public LiteralCommandNode command =
      Commands.literal("ecokills")
          .then(
              Commands.literal("reload")
                  .executes(
                      context -> {
                        EcoKills.getPlugin(EcoKills.class).reloadConfig();
                        ;
                        context
                            .getSource()
                            .getSender()
                            .sendMessage(
                                Utils.colorize("<gold>EcoKills configuration reloaded!</gold>"));
                        return 1;
                      }))
          .build();
}
