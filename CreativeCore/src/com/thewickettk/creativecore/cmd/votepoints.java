package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import com.thewickettk.creativecore.mysql.statments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class votepoints implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("votepoints")){
            if (args.length == 0){
                if (sender instanceof Player) {
                    int pts = statments.getPlayerPoints(sender.getName());
                    sender.sendMessage(msg.back("&eYou currently have &a" + pts + "&e points available.",true ));
                    sender.sendMessage(msg.back("&eYou can get points by voting &c/vote.",true ));
                    return true;
                }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
            if (args.length == 1){
               if (statments.checkPlayerExists(args[0])){
                   int pts = statments.getPlayerPoints(args[0]);
                   sender.sendMessage(msg.back("&a" + args[0] + " &ehas &b" + pts + "&e points.", true));
                   return true;
               }
               sender.sendMessage(msg.back("&cThat player has not joined yet!",true));
               return true;
            }

            if (args.length == 2){
                if (args[0].equalsIgnoreCase("reset")){
                    if (sender instanceof Player){
                        if (!sender.hasPermission("creativecore.votepoints.admin")){
                            sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                            return true;
                        }
                    }
                    if (!statments.checkPlayerExists(args[1])){
                        sender.sendMessage(msg.back("&cThat player has never joined before!",true));
                        return true;
                    }
                    statments.setPlayerPoints(args[1],0);
                    sender.sendMessage(msg.back("&a" + args[1] + "'s points has been reset.",true));
                    return true;
                }
                if (args[0].equalsIgnoreCase("buy")){
                    if (args[1].equalsIgnoreCase("worldedit")){
                        if (sender instanceof Player){
                            int points = statments.getPlayerPoints(sender.getName());
                            int total = points - 1;
                            if ((total) < 0){
                                sender.sendMessage(msg.back("&cYou do not have enough points to purchase WorldEdit for 1 hour.", true));
                                return true;
                            }
                            if (!sender.hasPermission("worldedit.wand")) {
                                sender.sendMessage(msg.back("&eYou have purchased &bWorldEdit &efor the next &a1 hour.", true));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.history.undo true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.history.redo true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.navigation.up true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.selection.pos true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.wand true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.region.center true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.region.move true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.region.stack true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.generation.cylinder true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.generation.sphere true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.clipboard.copy true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.clipboard.cut true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.region.replace true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.region.set true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.clipboard.paste true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.clipboard.rotate true 1hr server=creative");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp worldedit.clipboard.flip true 1hr server=creative");
                                statments.setPlayerPoints(sender.getName(), total);
                                return true;
                            }
                            sender.sendMessage(msg.back("&cYou already have WorldEdit.", true));
                            return true;
                        }
                        sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("nightvision")){
                        if (sender instanceof Player){
                            int points = statments.getPlayerPoints(sender.getName());
                            int total = points - 1;
                            if ((total) < 0){
                                sender.sendMessage(msg.back("&cYou do not have enough points to purchase WorldEdit for 1 hour.", true));
                                return true;
                            }
                            if (!sender.hasPermission("builders.util.nightvision")) {
                                sender.sendMessage(msg.back("&eYou have purchased &bNightVision &efor the next &a1 hour.", true));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission settemp builders.util.nightvision true 1day server=creative");
                                statments.setPlayerPoints(sender.getName(), total);
                                return true;
                            }
                            sender.sendMessage(msg.back("&cYou already have NightVision. &7Use &8/nv &7to activate it.", true));
                            return true;
                        }
                        sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("Supervote")){
                        if (sender instanceof Player){
                            int points = statments.getPlayerPoints(sender.getName());
                            int total = points - 5;
                            if ((total) < 0) {
                                sender.sendMessage(msg.back("&cYou do not have enough points to purchase 1x BuildBattle Super votes.", true));
                                return true;
                            }
                            sender.sendMessage(msg.back("&eYou have purchased &b1x BuildBattle Super votes &e.", true));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "bba votes add 1 " + sender.getName());
                            statments.setPlayerPoints(sender.getName(), total);
                            return true;
                        }
                        sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                        return true;
                    }
                }
            }

            if (args.length == 3){
                if (args[0].equalsIgnoreCase("add")){
                    if (sender instanceof Player){
                        if (!sender.hasPermission("creativecore.votepoints.admin")){
                            sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                            return true;
                        }
                    }
                    if (!statments.checkPlayerExists(args[1])){
                        sender.sendMessage(msg.back("&cThat player has never joined before!",true));
                        return true;
                    }
                    int amt;
                    try{
                        amt = Integer.parseInt(args[2]);
                        if (amt < 0){
                            sender.sendMessage(msg.back("&cPlease enter a valid number!",true));
                            return true;
                        }
                    }catch(NumberFormatException e){
                        sender.sendMessage(msg.back("&cPlease enter a valid number!",true));
                        return true;
                    }
                    int points = statments.getPlayerPoints(args[1]);
                    int total = points + amt;
                    statments.setPlayerPoints(args[1], total);
                    sender.sendMessage(msg.back("&a" + amt + "&e vote point has been added to &b" + args[1],true));
                    Player player = Bukkit.getServer().getPlayer(args[1]);
                    if (player != null){
                        msg.sendP(player,"&a" + amt + " &e vote point has been given to you!", true);
                    }
                    return true;
                }

                if (args[0].equalsIgnoreCase("remove")){
                    if (sender instanceof Player){
                        if (!sender.hasPermission("creativecore.votepoints.admin")){
                            sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                            return true;
                        }
                    }
                    if (!statments.checkPlayerExists(args[1])){
                        sender.sendMessage(msg.back("&cThat player has never joined before!",true));
                        return true;
                    }
                    int amt;
                    try{
                        amt = Integer.parseInt(args[2]);
                        if (amt < 0){
                            sender.sendMessage(msg.back("&cPlease enter a valid number!",true));
                            return true;
                        }
                    }catch(NumberFormatException e){
                        sender.sendMessage(msg.back("&cPlease enter a valid number!",true));
                        return true;
                    }
                    int points = statments.getPlayerPoints(args[1]);
                    int total = points - amt;
                    if ((total) < 0){
                        sender.sendMessage(msg.back("&cPlayers are not allowed to have negative balances.", true));
                        return true;
                    }
                    statments.setPlayerPoints(args[1], total);
                    sender.sendMessage(msg.back("&a" + amt + "&e vote point has been removed from &b" + args[1],true));
                    return true;
                }
                if (args[0].equalsIgnoreCase("set")){
                    if (sender instanceof Player){
                        if (!sender.hasPermission("creativecore.votepoints.admin")){
                            sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                            return true;
                        }
                    }
                    if (!statments.checkPlayerExists(args[1])){
                        sender.sendMessage(msg.back("&cThat player has never joined before!",true));
                        return true;
                    }
                    int amt;
                    try{
                        amt = Integer.parseInt(args[2]);
                        if (amt < 0){
                            sender.sendMessage(msg.back("&cPlease enter a valid number!",true));
                            return true;
                        }
                    }catch(NumberFormatException e){
                        sender.sendMessage(msg.back("&cPlease enter a valid number!",true));
                        return true;
                    }

                    statments.setPlayerPoints(args[1],amt);
                    sender.sendMessage(msg.back("&b" + args[1] + "'s &epoints has been set to &a" + amt,true));
                    return true;
                }
            }
            sender.sendMessage(msg.back("&cUsages: /votepoints [player]",true));
            return true;
        }
        return false;
    }
}
