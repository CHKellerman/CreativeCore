package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gms implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gms")){
            if (args.length == 0){
                if (sender instanceof Player){
                    Player thatguy = Bukkit.getPlayer(sender.getName());
                    if (sender.hasPermission("creative.survival")){
                        if (thatguy.getGameMode() != GameMode.SURVIVAL) {
                            thatguy.setGameMode(GameMode.SURVIVAL);
                            msg.sendP(thatguy, "&eYour gamemode has been set to &aSurvival.", true);
                            return true;
                        }
                        msg.sendP(thatguy, "&cYou are already in that gamemode.", true);
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
                    if (!sender.hasPermission("creative.survival.other")){
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                if (theotherdude != null) {
                    if (theotherdude.getGameMode() != GameMode.SURVIVAL) {
                        theotherdude.setGameMode(GameMode.SURVIVAL);
                        msg.sendP(theotherdude, "&eYour gamemode has been set to &aSurvival.", true);
                        sender.sendMessage(msg.back("&eYou have set &b" + theotherdude.getName() + "&e to &aSurvival.", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&b" + theotherdude.getName() + "&e is already in &aSurvival.", true));
                    sender.sendMessage(msg.back("&b" + theotherdude.getName() + "&e is already in &aSurvival.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cCommand usage: /gms &7[player]",true));
            return true;
        }
        return false;
    }
}
