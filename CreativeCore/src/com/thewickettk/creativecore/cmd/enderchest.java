package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class enderchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("enderchest")){
            if (args.length == 0) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("creativecore.enderchest")) {
                        Player p = Bukkit.getPlayer(sender.getName());
                        p.openInventory(p.getEnderChest());
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
            if (args.length==1){
                if (sender instanceof Player){
                    if (sender.hasPermission("creativecore.enderchest.other")){
                        Player t = Bukkit.getServer().getPlayer(args[0]);
                        Player p = Bukkit.getPlayer(sender.getName());
                        if (t != null) {
                            p.openInventory(t.getEnderChest());
                            msg.sendP(p, "&eYou have opened &b" + t.getName() + "'s&e enderchest.", true);
                            return true;
                        }
                        sender.sendMessage(msg.back("&cThat player is not online!", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
            sender.sendMessage(msg.back("&cUsages: /enderchest &7[player]", true));
            return true;
        }
        return false;
    }
}
