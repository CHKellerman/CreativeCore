package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("feed")){
            if (args.length == 0){
                if (sender instanceof Player){
                    Player thatguy = Bukkit.getPlayer(sender.getName());
                    if (sender.hasPermission("creative.feed")){
                        thatguy.setFoodLevel(20);
                        sender.sendMessage(msg.back("&eYour food has been &bReplenished",true));
                        return true;
                    } else {
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
            if (args.length == 1){
                Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                if (theotherdude != null){
                    if (sender instanceof Player){
                        if (!sender.hasPermission("creative.feed.other")){
                            sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                            return true;
                        }
                    }
                    theotherdude.setFoodLevel(20);
                    msg.sendP(theotherdude ,"&eYour food has been &bReplenished" ,true);
                    sender.sendMessage(msg.back("&b" + theotherdude.getName() + "'s &efood has been &bReplenished",true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cCommand usage: /feed &7[player]",true));
            return true;
        }
        return false;
    }
}
