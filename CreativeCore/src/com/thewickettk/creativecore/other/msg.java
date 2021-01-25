package com.thewickettk.creativecore.other;

import com.thewickettk.creativecore.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class msg {
    private static String anotherstr;

    public static String back(String msg, Boolean prefix){
        anotherstr = "";
        if (prefix){
            anotherstr = Main.config.getString("prefix");
        }
        return (ChatColor.translateAlternateColorCodes('&',  anotherstr + msg));
    }
    public static void sendP(Player thatguy,String msg, Boolean prefix){
        anotherstr = "";
        if (prefix){
            anotherstr = Main.config.getString("prefix");
        }
        thatguy.sendMessage((ChatColor.translateAlternateColorCodes('&',  anotherstr + msg)));
    }
}
