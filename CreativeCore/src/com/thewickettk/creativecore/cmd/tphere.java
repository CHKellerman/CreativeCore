package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tphere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tphere")){
            if (sender instanceof Player){
                if (sender.hasPermission("creative.tphere")){
                    if (args.length == 1){
                        Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                        if (theotherdude !=null){
                            theotherdude.teleport(((Player) sender).getLocation());
                            sender.sendMessage(msg.back("&b" + theotherdude.getName() + "&e has been teleported to your location.",true));
                            return true;
                        }
                        sender.sendMessage(msg.back("&cThat player is not online!", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&cUsages: /tphere (player)", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                return true;
            }
            sender.sendMessage("(CreativeCore) You cant execute this command as console.");
            return true;
        }
        return false;
    }
}
