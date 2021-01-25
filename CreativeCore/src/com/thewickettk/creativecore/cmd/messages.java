package com.thewickettk.creativecore.cmd;

import com.google.common.collect.Maps;
import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;


public class messages implements CommandExecutor {
    public static Map<Player, Player> reply = Maps.newHashMap();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("message")) {
            if (args.length == 0) {
                sender.sendMessage(msg.back("&cUsages: /messages (Player) {messages}", true));
                return true;
            }
            if (args.length == 1) {
                sender.sendMessage(msg.back("&cUsages: /messages (Player) {messages}", true));
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            
            if (target != null) {

                for (int i = 1; i < args.length; i++) {
                    msg = msg + (args[i] + " ");
                }
            }
            sender.sendMessage(msg.back("&cThat player is not online!", true));
            return true;
            sender.sendMessage(com.thewickettk.creativecore.other.msg.back("&8[&cMe &e&l->&b " + target.getName() + " &8]&e " + msg, false));
            com.thewickettk.creativecore.other.msg.sendP(target, "&8[&b" + sender.getName() + " &e&l-> &cMe&8]&e " + msg, false);
            Player p = Bukkit.getPlayer(sender.getName());
            if (reply.containsKey(p)){
                reply.remove(p);
            }
            reply.put(target,p);
            if (!socialspy.socialspy.isEmpty()){
                for (int i = 1; i < socialspy.socialspy.size(); i++){
                    Player pt = socialspy.socialspy.get(i);
                    if (pt !=null){
                        com.thewickettk.creativecore.other.msg.sendP( pt, "&8[&eSocialSpy&8]&7 " + sender.getName() + "&8->&7 " + target.getName() + "&8: &7" + msg ,false);
                    }
                    socialspy.socialspy.remove(i);
                }
            }
            return true;
        }
        return false;
    }
}
