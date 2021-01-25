package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import com.thewickettk.creativecore.mysql.statments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class marryinfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("marryinfo")){
            if (args.length == 0){
                Player p = Bukkit.getPlayer(sender.getName());
                if (statments.getisMarried(p.getName())){
                    String marriedto = statments.getPlayerMarriedTo(p.getName());
                    msg.sendP(p,"&eYou are married to &b" + marriedto + "&e.",true);
                    return true;
                }
                msg.sendP(p,"&eYou are &cnot&e not married to anyone.",true);
                return true;
            }
            if (args.length == 1){
                Player t = Bukkit.getServer().getPlayer(args[0]);
                if (t != null){
                    Player p = Bukkit.getPlayer(sender.getName());

                    if (statments.checkMarriedPlayerExists(t.getName())) {
                        if (statments.getisMarried(t.getName())) {
                            String marriedto = statments.getPlayerMarriedTo(t.getName());
                            msg.sendP(p, "&b" + t + "&e is married to &b" + marriedto + "&e.", true);
                            return true;
                        }
                    }
                    msg.sendP(p,"&b" + t + "&e is not married to anyone.",true);
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!",true));
                return true;
            }
            sender.sendMessage(msg.back("&cUsages: /marryinfo [player]",true));
        }
        return false;
    }
}
