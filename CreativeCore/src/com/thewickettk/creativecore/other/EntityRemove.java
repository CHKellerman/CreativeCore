package com.thewickettk.creativecore.other;

import com.google.common.collect.Lists;
import com.thewickettk.creativecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import java.util.List;
import java.util.Random;

public class EntityRemove {
    public static List<Entity> entities = Lists.newArrayList();
    public static int seconds = 1;

    public static void EntityClear(){
        entities.clear();
        for (World currentWorld: Bukkit.getServer().getWorlds()){
            for (Entity currentEntity: Bukkit.getServer().getWorld(currentWorld.getName()).getEntities()){
                if (currentEntity instanceof Item){
                    currentEntity.remove();
                    if (Main.config.getBoolean("debug")) {
                        System.out.println("(CreativeCore - Debug) Executed Entity clear Runnable.");
                    }
                }
            }
        }
        ++seconds;
        if (seconds >= (Main.config.getInt("ReactionTime")*60)){
            seconds = 1;
            if (ChatReaction.getOnlineTotal() >= 5) {
                Random rand = new Random();
                int random = rand.nextInt(2);
                if (random == 1){
                    ChatReaction.chatReactionStart(true);
                    return;
                }
                ChatReaction.chatReactionStart(false);
                return;
            }
        }
        if (seconds >= (3*60)){
            if (ChatReaction.ReactionOnGoing){
                ChatReaction.chatReactionExspired();
            }
        }
    }
}
