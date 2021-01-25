package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.Main;
import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")){
            Main.spawn = getSpawn();
            if (args.length == 0){
                if (sender instanceof Player) {
                    Player thatguy = Bukkit.getPlayer(sender.getName());
                    thatguy.teleport(Main.spawn);
                    sender.sendMessage(msg.back("&eYou have been teleported to spawn.", true));
                    return true;
                }
                sender.sendMessage("(CreativeCore) You cant execute this command as console.");
                return true;
            }
            if (args.length == 1){
                if (sender instanceof Player){
                    if (!sender.hasPermission("creative.spawn.other")){
                        sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                        return true;
                    }
                }
                Player theotherguy = Bukkit.getServer().getPlayer(args[0]);
                if (theotherguy != null){
                    theotherguy.teleport(Main.spawn);
                    sender.sendMessage(msg.back("&a"+ theotherguy.getName() + "&e has been teleported to spawn.", true));
                    msg.sendP(theotherguy,"&eYou have been teleported to spawn.",true);
                    return true;
                }
                sender.sendMessage(msg.back("&cThat player is not online!", true));
                return true;
            }
            sender.sendMessage(msg.back("&cUsages: /spawn &8[player]", true));
            return true;
        }
        return false;
    }

    public static Location getSpawn(){
        Location loc = new Location(Bukkit.getWorld(Main.config.getString("spawn.world")), Main.config.getDouble("spawn.posX"), Main.config.getDouble("spawn.posY"), Main.config.getDouble("spawn.posZ"));
        loc.setPitch((float) Main.config.getDouble("spawn.pitch"));
        loc.setYaw((float) Main.config.getDouble("spawn.yaw"));
        return loc;
    }
}
