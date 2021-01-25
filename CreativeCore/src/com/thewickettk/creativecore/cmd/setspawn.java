package com.thewickettk.creativecore.cmd;

import com.thewickettk.creativecore.Main;
import com.thewickettk.creativecore.other.msg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setspawn  implements CommandExecutor {
    private static setspawn instance = new setspawn();
    public static setspawn getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspawn")){
            if (sender instanceof Player){
                if (sender.hasPermission("creative.setspawn")){
                    Player thatguy = Bukkit.getPlayer(sender.getName());
                    Location newspawn = new Location(Bukkit.getWorld(thatguy.getWorld().getName()), thatguy.getLocation().getX(), thatguy.getLocation().getY(),thatguy.getLocation().getZ());
                    newspawn.setPitch(thatguy.getLocation().getPitch());
                    newspawn.setYaw(thatguy.getLocation().getYaw());
                    Main.spawn = newspawn;
                    Main.config.set("spawn.world", Main.spawn.getWorld().getName());
                    Main.config.set("spawn.posX", Main.spawn.getX());
                    Main.config.set("spawn.posY", Main.spawn.getY());
                    Main.config.set("spawn.posZ", Main.spawn.getZ());
                    Main.config.set("spawn.pitch", Main.spawn.getPitch());
                    Main.config.set("spawn.yaw", Main.spawn.getYaw());
                    msg.sendP(thatguy,"&eSpawn has been set!",true);
                    return true;
                }
                sender.sendMessage(msg.back("&cYou do not have access to this command.", true));
                return true;
            }
            sender.sendMessage(msg.back("&cUsages: /setspawn", true));
            return true;
        }
        return false;
    }
}
