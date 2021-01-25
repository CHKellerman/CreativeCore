package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class invsee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("invsee")){
            if (sender instanceof Player){
                if (args.length == 1){
                    if (sender.hasPermission("creativecore.invsee")){
                        Player p = Bukkit.getPlayer(sender.getName());
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            msg.sendP(p, "&eYou have opened &b" + target.getName() + "'s&e inventory.", true);
                            p.openInventory(target.getInventory());
                            return true;
                        }
                        sender.sendMessage(msg.back("&cThat player is not online!", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cCommand usage: /invsee &7[player]",true));
                return true;
            }
            sender.sendMessage("(CreativeCore) You cant execute this command as console.");
            return true;
        }
        return false;
    }
}
