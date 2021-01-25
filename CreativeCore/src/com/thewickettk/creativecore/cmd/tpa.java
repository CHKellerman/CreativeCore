package com.thewickettk.creativecore.cmd;

import com.google.common.collect.Maps;
import com.thewickettk.creativecore.Main;
import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class tpa implements CommandExecutor {
    public static Map<Player, Player> tpas = Maps.newHashMap();
    private final Map<Player, Long> cooldown = Maps.newHashMap();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpa")){
            if (sender instanceof Player){
                int cmdcooldown = Main.config.getInt("tpacooldown");
                if (args.length == 1){
                    Player thatguy = Bukkit.getPlayer(sender.getName());
                    Player theotherdude = Bukkit.getServer().getPlayer(args[0]);
                    if (cooldown.containsKey(thatguy)){
                        long secLeft = (cooldown.get(thatguy)/1000+cmdcooldown) - (System.currentTimeMillis()/1000);
                        if(secLeft>0) {
                            sender.sendMessage(msg.back("&eYou can use this command for another &c" + secLeft + "&e seconds!",true));
                            return true;
                        }
                    }
                    if (theotherdude !=null){
                        cooldown.put(thatguy, System.currentTimeMillis());
                        tpas.put(theotherdude, thatguy);
                        msg.sendP(thatguy, "&eYou have requested to teleport to &a" + theotherdude.getName(),true);
                        msg.sendP(theotherdude, "&b" + thatguy.getName() +"&e has requested to teleport to &ayour location.",true);
                        msg.sendP(theotherdude,"&aTo accept the request execute: &e/tpaccept",true);
                        msg.sendP(theotherdude,"&cTo deny the request execute: &e/tpdeny",true);
                        return true;
                    }
                    sender.sendMessage(msg.back("&cThat player is not online!", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cCommand usage: /tpa (player)",true));
                return true;
            }
            sender.sendMessage("(CreativeCore) You cant execute this command as console.");
            return true;
        }
        return false;
    }
}
