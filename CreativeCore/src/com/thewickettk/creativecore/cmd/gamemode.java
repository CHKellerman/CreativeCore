package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player thatguy = Bukkit.getPlayer(sender.getName());
                if ((args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s"))) {
                    if (sender.hasPermission("creative.survival")) {
                        if (thatguy.getGameMode() != GameMode.SURVIVAL) {
                            thatguy.setGameMode(GameMode.SURVIVAL);
                            msg.sendP(thatguy, "&eYour gamemode has been set to &aSurvival.", true);
                            return true;
                        }
                        msg.sendP(thatguy, "&cYou are already in that gamemode.", true);
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                if ((args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c"))) {
                    if (sender.hasPermission("creative.creative")) {
                        if (thatguy.getGameMode() != GameMode.CREATIVE) {
                            thatguy.setGameMode(GameMode.CREATIVE);
                            msg.sendP(thatguy, "&eYour gamemode has been set to &aCreative.", true);
                            return true;
                        }
                        msg.sendP(thatguy, "&cYou are already in that gamemode.", true);
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                if ((args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a"))) {
                    if (sender.hasPermission("creative.adventure")) {
                        if (thatguy.getGameMode() != GameMode.ADVENTURE) {
                            thatguy.setGameMode(GameMode.ADVENTURE);
                            msg.sendP(thatguy, "&eYour gamemode has been set to &aAdventure.", true);
                            return true;
                        }
                        msg.sendP(thatguy, "&cYou are already in that gamemode.", true);
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                if ((args[0].equalsIgnoreCase("spectate") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spec"))) {
                    if (sender.hasPermission("creative.spectate")) {
                        if (thatguy.getGameMode() != GameMode.SPECTATOR) {
                            thatguy.setGameMode(GameMode.SPECTATOR);
                            msg.sendP(thatguy, "&eYour gamemode has been set to &aSpectate.", true);
                            return true;
                        }
                        msg.sendP(thatguy, "&cYou are already in that gamemode.", true);
                        return true;
                    }
                    sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cCommand usage: /gamemode (gamemode) &7[player]",true));
                return true;
            }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
        }
        if (args.length == 2) {
            Player theotherdude = Bukkit.getServer().getPlayer(args[1]);
            if ((args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) || args[0].equalsIgnoreCase("s")) {
                if (sender instanceof Player) {
                    if (!sender.hasPermission("creative.survival.other")) {
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                if (theotherdude != null) {
                    if (theotherdude.getGameMode() != GameMode.SURVIVAL) {
                        theotherdude.setGameMode(GameMode.SURVIVAL);
                        msg.sendP(theotherdude, "&eYour gamemode has been set to &aSurvival.", true);
                        sender.sendMessage(msg.back("&eYou have set &b" + theotherdude.getName() + "&e to &aSurvival.", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&a" + theotherdude.getName() + "&e is already in &aSurvival.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            if ((args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c"))) {
                if (sender instanceof Player) {
                    if (!sender.hasPermission("creative.creative.other")) {
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                if (theotherdude != null) {
                    if (theotherdude.getGameMode() != GameMode.CREATIVE) {
                        theotherdude.setGameMode(GameMode.CREATIVE);
                        msg.sendP(theotherdude, "&eYour gamemode has been set to &aCreative.", true);
                        sender.sendMessage(msg.back("&eYou have set &b" + theotherdude.getName() + "&e to &aCreative.", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&b" + theotherdude.getName() + "&e is already in &aCreative.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            if ((args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a"))) {
                if (sender instanceof Player) {
                    if (!sender.hasPermission("creative.adventure.other")) {
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                if (theotherdude != null) {
                    if (theotherdude.getGameMode() != GameMode.ADVENTURE) {
                        theotherdude.setGameMode(GameMode.ADVENTURE);
                        msg.sendP(theotherdude, "&eYour gamemode has been set to &aAdventure.", true);
                        sender.sendMessage(msg.back("&eYou have set &b" + theotherdude.getName() + "&e to &aAdventure.", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&b" + theotherdude.getName() + "&e is already in &aAdventure.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            if ((args[0].equalsIgnoreCase("spectate") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spec"))) {
                if (sender instanceof Player) {
                    if (!sender.hasPermission("creative.spectate.other")) {
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                if (theotherdude != null) {
                    if (theotherdude.getGameMode() != GameMode.SPECTATOR) {
                        theotherdude.setGameMode(GameMode.SPECTATOR);
                        msg.sendP(theotherdude, "&eYour gamemode has been set to &aSpectate.", true);
                        sender.sendMessage(msg.back("&eYou have set &b" + theotherdude.getName() + "&e to &aSpectate.", true));
                        return true;
                    }
                    sender.sendMessage(msg.back("&b" + theotherdude.getName() + "&e is already in &aSpectate.", true));
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cCommand usage: /gamemode (gamemode) &7[player]",true));
            return true;
        }
        return false;
    }
}
