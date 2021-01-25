package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.OpenBook;
import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("help")){
            if (sender instanceof Player){
                Player p = Bukkit.getPlayer(sender.getName());
                OpenBook.openBook(OpenBook.createBook(p),p);
                msg.sendP(p,"&eYou have open the Creative Guideline",true);
                return true;
            }
            sender.sendMessage("(CreativeCore) You cant execute this command as console. Function : OpenSpawnBook");
            return true;
        }
        return false;
    }
}
