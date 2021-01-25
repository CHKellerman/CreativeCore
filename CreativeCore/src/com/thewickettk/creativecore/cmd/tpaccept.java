package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpaccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpaccept")){
            if (sender instanceof Player){
                Player thatguy = Bukkit.getPlayer(sender.getName());
                Player requester = tpa.tpas.get(thatguy);
                if (requester == null){
                    msg.sendP(thatguy, "&eYou have no teleport requests",true);
                    return true;
                }
                tpa.tpas.remove(thatguy);
                requester.teleport(thatguy.getLocation());
                msg.sendP(thatguy,"&eYou have &aaccepted &b" + requester.getName() + "&e teleport request.", true);
                msg.sendP(requester, "&b" + thatguy.getName() + "&a accepted &eyour teleport request.",true );
                return true;
            }else {
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
        }
        return false;
    }
}
