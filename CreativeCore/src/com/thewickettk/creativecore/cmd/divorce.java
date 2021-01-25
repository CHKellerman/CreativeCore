package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import com.thewickettk.creativecore.mysql.statments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class divorce implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("divorce")){
            if (args.length == 0){
                Player p = Bukkit.getPlayer(sender.getName());
                if (statments.getisMarried(p.getName())){
                    String t = statments.getPlayerMarriedTo(p.getName());
                    statments.setisMarried(p.getName(),0);
                    statments.setisMarried(t,0);
                    statments.setMarriedTo(p.getName(), "");
                    statments.setMarriedTo(t,"");
                    msg.sendP(p,"&eYou have divorced with " + t + ".",true);
                    Player Pt = Bukkit.getServer().getPlayer(t);
                    if (Pt != null){
                        msg.sendP(Pt, "&b" + p.getName() + " &e has &cdivorced &ewith you.&7 Probably not meant to be...",true);
                    }
                    for (Player current: Bukkit.getServer().getOnlinePlayers()){
                        msg.sendP(current, "&8" + p.getName() + " &7has divorced with &8" + t+ "&7.",true);
                    }
                    return true;
                }
                msg.sendP(p,"&eYou are not married to anyone, therefore you cant divorce with anyone.&7 I know it i" +
                        "s a sad and lonely life",true);
                return true;
            }
            sender.sendMessage(msg.back("&cUsages: /divorce",true));
        }
        return false;
    }
}
