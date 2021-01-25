package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.ChatReaction;
import com.thewickettk.creativecore.other.EntityRemove;
import com.thewickettk.creativecore.other.msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Random;

public class Reaction implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reaction")){
            if (sender.hasPermission("creative.reaction")){
                EntityRemove.seconds = 1;
                Random rand = new Random();
                int random = rand.nextInt(2);
                if (random == 1){
                    ChatReaction.chatReactionStart(true);
                    sender.sendMessage(msg.back("&eYou have started ChatReaction.",true));
                    return true;
                }
                ChatReaction.chatReactionStart(false);
                sender.sendMessage(msg.back("&eYou have started ChatReaction.",true));
                return true;
            }
        }
        return false;
    }
}
