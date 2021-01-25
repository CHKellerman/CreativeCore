package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class marryno implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("marryno")){
            Player p = Bukkit.getServer().getPlayer(sender.getName());
            Player t = marry.marryReq.get(p);
            if (t == null){
                msg.sendP(p,"&eUnfortunately you have no marriage request.&7 Very sad",true);
                return true;
            }
            marry.marryReq.remove(p);
            msg.sendP(p,"&eYou have &cDenied &b" + t.getName() + " &emarriage request.", true);
            msg.sendP(t,"&b" + p.getDisplayName() + "&e has unfortunately &cDenied &eyour marriage request.&7 Maybe its just not meant to be",true);
            return true;
        }
        return false;
    }
}
