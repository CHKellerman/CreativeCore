package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class teleport implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("teleport")){
            if (args.length == 1) {
                if (sender instanceof Player){
                    if (sender.hasPermission("creative.teleport")){
                        Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                            if (theotherdude != null){
                                tpPlayerTo((Player) sender,theotherdude);
                                sender.sendMessage(msg.back("&eYou have been teleported to &b" + theotherdude.getName() ,true));
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
            if (args.length == 2){
                if (sender instanceof Player) {
                    if (!sender.hasPermission("creative.teleport.other")) {
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                Player thatguy = Bukkit.getServer().getPlayer(args[0]);
                Player theotherdude = Bukkit.getServer().getPlayer(args[1]);
                if (thatguy != null && theotherdude != null){
                    tpPlayerTo(thatguy,theotherdude);
                    sender.sendMessage(msg.back("&b" + thatguy.getName() + "&e has been teleported to &b" + theotherdude.getName(),true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThe player(s) you have targeted is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cUsages: /teleport (player) &8[player]", true));
            return true;
        }
        return false;
    }

    public static void tpPlayerTo(Player user, Player target){
        user.teleport(target);
    }
}
