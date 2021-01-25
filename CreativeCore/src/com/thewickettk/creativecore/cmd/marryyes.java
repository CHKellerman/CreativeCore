package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import com.thewickettk.creativecore.mysql.statments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class marryyes  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("marryyes")) {
            Player p = Bukkit.getServer().getPlayer(sender.getName());
            Player t = marry.marryReq.get(p);
            if (t == null){
                msg.sendP(p,"&eUnfortunately you have no marriage request.&7 Very sad",true);
                return true;
            }
            marry.marryReq.remove(p);
            statments.setMarriedTo(p.getName(),t.getName());
            statments.setisMarried(p.getName(),1);
            statments.setMarriedTo(t.getName(),p.getName());
            statments.setisMarried(t.getName(),1);
            msg.sendP(p, "&eYou have accepted &b" + t.getName() + "&e marriage proposal.",true);
            msg.sendP(t,"&b" + p.getName() + "&e has &aAccepted &eyour marriage proposal.",true);
            for (Player current: Bukkit.getServer().getOnlinePlayers()){
                msg.sendP(current, "&8" + p.getName() + "&7 has married &8" + t.getName(),true);
            }
            return true;
        }
        return false;
    }
}
