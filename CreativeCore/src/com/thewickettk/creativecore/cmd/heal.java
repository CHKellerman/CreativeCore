package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class heal implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("heal")){
            if (args.length == 0){
                if (sender instanceof Player){
                    if (sender.hasPermission("creative.heal")){
                        Player thatguy = Bukkit.getPlayer(sender.getName());
                        thatguy.setFoodLevel(20);
                        thatguy.setHealth(20);
                        for (PotionEffect effect : thatguy.getActivePotionEffects())
                            thatguy.removePotionEffect(effect.getType());
                        sender.sendMessage(msg.back("&eYour health has been &aReplenished",true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
            if (args.length == 1){
                Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                if (theotherdude != null){
                    if (sender instanceof Player){
                        if (!sender.hasPermission("creative.heal.other")){
                            sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                            return true;
                        }
                    }
                    theotherdude.setFoodLevel(20);
                    Player thatguy = Bukkit.getPlayer(sender.getName());
                    theotherdude.setFoodLevel(20);
                    theotherdude.setHealth(20);
                    for (PotionEffect effect : theotherdude.getActivePotionEffects())
                        theotherdude.removePotionEffect(effect.getType());
                    msg.sendP(theotherdude ,"&eYour health has been &aReplenished" ,true);
                    sender.sendMessage(msg.back("&b" + theotherdude.getName() + "'s &ehealth has been &aReplenished",true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cCommand usage: /heal &7[player]",true));
            return true;
        }
        return false;
    }
}
