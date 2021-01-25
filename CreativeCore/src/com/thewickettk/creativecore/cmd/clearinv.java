package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class clearinv implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearinv")){
            if (args.length == 0){
                if (sender instanceof Player){
                    if (sender.hasPermission("creativecore.clearinv")){
                        Player p = Bukkit.getPlayer(sender.getName());
                        p.getInventory().clear();
                        msg.sendP(p,"&eYou have cleared your inventory.",true);
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
            if (args.length == 1){
                if (sender instanceof Player){
                    if (!sender.hasPermission("creativecore.clearinv.other")){
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target != null){
                    target.getInventory().clear();
                    msg.sendP(target,"&eYour inventory was cleared.",true);
                    sender.sendMessage(msg.back("&eYou have cleared &b" + target.getName() + "'s &einventory.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cCommand usage: /clearinv &7[player]",true));
            return true;
        }
        return false;
    }
}
