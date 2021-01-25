package com.thewickettk.creativecore.cmd;

import com.google.common.collect.Maps;
import com.thewickettk.creativecore.Main;
import com.thewickettk.creativecore.other.msg;
import com.thewickettk.creativecore.mysql.statments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Map;

public class marry implements CommandExecutor {
    public static Map<Player, Player> marryReq = Maps.newHashMap();
    private final Map<Player, Long> cooldown = Maps.newHashMap();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("marry")) {
            if (args.length == 1){
                int cmdCoolDown = Main.config.getInt("marrycooldown");
                Player p = Bukkit.getPlayer(sender.getName());
                if (statments.getisMarried(p.getName())){
                    String mariedto = statments.getPlayerMarriedTo(p.getName());
                    msg.sendP(p, "&eYou are married to &b" + mariedto + "&e.&7 Not working out? &8&l/divorce",true);
                    return true;
                }
                Player t = Bukkit.getServer().getPlayer(args[0]);
                if (t != null){
                    if (cooldown.containsKey(p)) {
                        long secleft = (cooldown.get(p) / 1000 + cmdCoolDown) - (System.currentTimeMillis() / 1000);
                        if (secleft > 0) {
                            sender.sendMessage(msg.back("&eYou can use this command for another &c" + secleft + "&e seconds!", true));
                            return true;
                        }
                    }
                    if ( t.getName() != p.getName()){
                        if (!statments.getisMarried(t.getName())){
                            cooldown.put(p, System.currentTimeMillis());
                            marryReq.put(t,p);
                            msg.sendP(p, "&eYou have asked &b" + t.getName() + "&e to marry you.&7 goodluck", true);
                            msg.sendP(t,"&b" + p.getName() + "&e has asked you to marry them.",true);
                            msg.sendP(t,"&aTo accept the request execute: &e/marryyes",true);
                            msg.sendP(t,"&cTo deny the request execute: &e/marryno",true);
                            return true;
                        }
                        msg.sendP(p, "&b" + t.getName() + "&e is &calready &emarried to someone",true);
                        return true;
                    }
                    msg.sendP(p, "&eYou have to be quite lonely to marry yourself.",true);
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cCommand usage: /marry (player)",true));
            return true;
        }
        return false;
    }
}
