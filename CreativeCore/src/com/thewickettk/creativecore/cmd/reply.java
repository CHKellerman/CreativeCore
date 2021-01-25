package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reply implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reply")){
            Player p = Bukkit.getServer().getPlayer(sender.getName());
            if (messages.reply.containsKey(p)){
                System.out.println(messages.reply.get(p));
                String out = "";
                for (String cur: args){
                    out = out + " " + cur;
                }
                Player target = Bukkit.getServer().getPlayer(messages.reply.get(p).getName());
                if (target == null){
                    msg.sendP(p,"&cThat player is not online anymore!",true);
                    return true;
                }
                sender.sendMessage(com.thewickettk.creativecore.other.msg.back("&8[&cMe &e&l->&b " + target.getName() + " &8]&e " + out, false));
                com.thewickettk.creativecore.other.msg.sendP(target, "&8[&b" + sender.getName() + " &e&l-> &cMe&8]&e " + out, false);
                if (messages.reply.containsValue(p)){
                    messages.reply.remove(p);
                }
                messages.reply.put(target,p);
                if (!socialspy.socialspy.isEmpty()){
                    for (int i = 1; i < socialspy.socialspy.size(); i++){
                        Player pt = socialspy.socialspy.get(i);
                        if (pt !=null){
                            com.thewickettk.creativecore.other.msg.sendP( pt, "&8[&eSocialSpy&8]&7 " + sender.getName() + "&8->&7 " + target.getName() + "&8: &7" + out ,false);
                        }
                        socialspy.socialspy.remove(i);
                    }
                }
                return true;
            }
            msg.sendP(p, "&cYou have no one to reply to.",true);
            return true;
        }
        return false;
    }
}
