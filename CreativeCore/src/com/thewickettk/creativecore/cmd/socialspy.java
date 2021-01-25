package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class socialspy implements CommandExecutor {
    public static ArrayList<Player> socialspy = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("socialspy")){
            if (sender instanceof Player){
                if (sender.hasPermission("creativecore.socialspy")){
                    Player p = Bukkit.getPlayer(sender.getName());
                    if (socialspy.contains(p)){
                        socialspy.remove(p);
                        msg.sendP(p,"&eYou have &cdisabled &eSocialspy.",true);
                        return true;
                    }
                    socialspy.add(p);
                    msg.sendP(p,"&eYou have &aenabled &eSocialspy.",true);
                    return true;
                }
                sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                return true;
            }
            sender.sendMessage("(CreativeCore) You cant execute this command as console.");
            return true;
        }
        return false;
    }
}
