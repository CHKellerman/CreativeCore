package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpdeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            if (sender instanceof Player) {
                Player thatguy = Bukkit.getPlayer(sender.getName());
                Player requester = tpa.tpas.get(thatguy);
                if (requester == null) {
                    msg.sendP(thatguy, "&eYou have no teleport requests", true);
                    return true;
                }
                tpa.tpas.remove(thatguy);
                msg.sendP(thatguy, "&eYou have &cdenied &b" + requester.getName() + "&e teleport request.", true);
                msg.sendP(requester, "&b" + thatguy.getName() + " &cdeneid &ayour teleport request.", true);
            }
            sender.sendMessage("(CreativeCore) You cant execute this command as console.");
            return true;
        }
        return false;
    }
}
