package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flight implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fly")){
            if (args.length == 0){
                if (sender instanceof Player) {
                    if (sender.hasPermission("creative.fly")) {
                        Player thatguy = Bukkit.getPlayer(sender.getName());
                        if (thatguy.getAllowFlight()){
                            thatguy.setAllowFlight(false);
                            thatguy.setFlying(false);
                            msg.sendP(thatguy, "&eYou have &cDisabled&e your fly.",true);
                            return true;
                        }
                        thatguy.setAllowFlight(true);
                        thatguy.setFlying(true);
                        msg.sendP(thatguy, "&eYou have &aEnabled&e your fly.",true);
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
                    if (sender.hasPermission("creative.fly.other")){
                        Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                        if (theotherdude !=null){
                            if (theotherdude.getAllowFlight()){
                                theotherdude.setFlying(false);
                                theotherdude.setAllowFlight(false);
                                sender.sendMessage(msg.back("&b"+ theotherdude.getName()+" &eflight has been &cDisabled.",true));
                                msg.sendP(theotherdude,"&eYour fly has been &cDisabled&e.",true);
                                return true;
                            }
                            if (!theotherdude.getAllowFlight()){
                                theotherdude.setAllowFlight(true);
                                theotherdude.setFlying(true);
                                sender.sendMessage(msg.back("&e"+ theotherdude.getName()+" flight has been &aEnabled.",true));
                                msg.sendP(theotherdude,"&eYour fly has been0 &aEnabled&e.",true);
                                return true;
                            }
                        System.err.println("(CreativeCore) Fly Error report to author!");
                        }
                        sender.sendMessage(msg.back("&cThat player is not online!", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                if (theotherdude !=null){
                    if (theotherdude.getAllowFlight()){
                        theotherdude.setFlying(false);
                        theotherdude.setAllowFlight(false);
                        sender.sendMessage(msg.back("&b"+ theotherdude.getName()+"&e flight has been &cDisabled.",true));
                        msg.sendP(theotherdude,"&eYour fly has been &cDisabled&e.",true);
                        return true;
                    } else {
                        theotherdude.setFlying(true);
                        theotherdude.setAllowFlight(true);
                        sender.sendMessage(msg.back("&b" + theotherdude.getName() + "&e flight has been &aEnabled.", true));
                        msg.sendP(theotherdude, "&eYour fly has been0 &aEnabled&e.", true);
                        return true;
                    }
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cUsage: /fly &7(player)",true));
            return true;
        }
        return false;
    }
}
