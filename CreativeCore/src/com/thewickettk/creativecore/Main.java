package com.thewickettk.creativecore;

import com.thewickettk.creativecore.cmd.*;
import com.thewickettk.creativecore.mysql.mysqlMain;
import com.thewickettk.creativecore.mysql.statments;
import com.thewickettk.creativecore.other.ChatReaction;
import com.thewickettk.creativecore.other.EntityRemove;
import com.thewickettk.creativecore.other.OpenBook;
import com.thewickettk.creativecore.other.msg;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public class Main extends JavaPlugin implements Listener {
    public static FileConfiguration config;
    public static Location spawn;
    private Main Instance;

    public void onEnable() {
        Instance = this;
        File file = new File(getDataFolder() + "config.yml");
        if (!(file.exists())) {
            this.saveResource("config.yml", false);
        }
        config = getConfig();
        mysqlMain.getInstance().mysqlConnect();
        registerCmd();
        statments.createPlayerDataTable();
        statments.createMarryDataTable();
        getServer().getPluginManager().registerEvents(this, this);
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                EntityRemove.EntityClear();
            }
        }, 0L, 20L);
        ChatReaction.getWordlist();
    }

    public void onDisabled() {

    }

    public void registerCmd() {
        getCommand("fly").setExecutor(new flight());
        getCommand("gamemode").setExecutor(new gamemode());
        getCommand("gma").setExecutor(new gma());
        getCommand("gmc").setExecutor(new gmc());
        getCommand("gms").setExecutor(new gms());
        getCommand("gmspec").setExecutor(new gmspec());
        getCommand("feed").setExecutor(new feed());
        getCommand("heal").setExecutor(new heal());
        getCommand("teleport").setExecutor(new teleport());
        getCommand("tphere").setExecutor(new tphere());
        getCommand("tpa").setExecutor(new tpa());
        getCommand("tpaccept").setExecutor(new tpaccept());
        getCommand("tpdeny").setExecutor(new tpdeny());
        getCommand("setspawn").setExecutor(new setspawn());
        getCommand("spawn").setExecutor(new spawn());
        getCommand("votepoints").setExecutor(new votepoints());
        getCommand("clearinv").setExecutor(new clearinv());
        getCommand("enderchest").setExecutor(new enderchest());
        getCommand("invsee").setExecutor(new invsee());
        getCommand("message").setExecutor(new messages());
        getCommand("socialspy").setExecutor(new socialspy());
        getCommand("divorce").setExecutor(new divorce());
        getCommand("marry").setExecutor(new marry());
        getCommand("marryinfo").setExecutor(new marryinfo());
        getCommand("marryyes").setExecutor(new marryyes());
        getCommand("marryno").setExecutor(new marryno());
        getCommand("help").setExecutor(new help());
        getCommand("reaction").setExecutor(new Reaction());
        getCommand("reply").setExecutor(new reply());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = (Player) e.getPlayer();
        Boolean pExistPoints = statments.checkPlayerExists(e.getPlayer().getName());
        Boolean pExistMarry = statments.checkMarriedPlayerExists(e.getPlayer().getName());
        if (!pExistPoints) {
            statments.createPlayer(player.getName());
        }
        if (!pExistMarry){
            statments.createMarriedPlayer(player.getName());
        }
        e.setJoinMessage("");
        e.getPlayer().setGameMode(GameMode.CREATIVE);
        player.teleport(com.thewickettk.creativecore.cmd.spawn.getSpawn());
        OpenBook.openBook(OpenBook.createBook(player),player);
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent e) {
        Player player = Bukkit.getServer().getPlayer(e.getPlayer().getName());
        e.setQuitMessage("");
        if (socialspy.socialspy.contains(e.getPlayer().getName())) {
            socialspy.socialspy.remove(e.getPlayer().getName());
        }
    }

    @EventHandler
    public void BlockFromToEvent(BlockFromToEvent e) {
        e.setCancelled(true);
        if (Main.config.getBoolean("debug")) {
            System.out.println("(CreativeCore - Debug) Executed BlockFromToEvent for player");
        }
    }

    @EventHandler
    public void PortalCreateEvent(PortalCreateEvent e) {
        e.setCancelled(true);
        if (Main.config.getBoolean("debug")) {
            System.out.println("(CreativeCore - Debug) Executed PortalCreateEvent for player " + e.getEntity().getName());
        }
    }

    @EventHandler
    public void PlayerDropItemEvent(PlayerDropItemEvent e) {
        e.getItemDrop().remove();
        if (Main.config.getBoolean("debug")) {
            System.out.println("(CreativeCore - Debug) Executed PlayerDropItemEvent for player " + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void PotionSplashEvent(PotionSplashEvent e) {
        e.setCancelled(true);
        if (Main.config.getBoolean("debug")) {
            System.out.println("(CreativeCore - Debug) Executed PotionSplashEvent for player " + e.getEntity().getName());
        }
    }

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e) {
        e.setDropItems(false);
        if (Main.config.getBoolean("debug")) {
            System.out.println("(CreativeCore - Debug) Executed BlockBreakEvent.");
        }
    }

    @EventHandler
    public void BlockPhysicsEvent(BlockPhysicsEvent e) {
        e.setCancelled(true);
        // do not add a Debug msg it brakes server tk...
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (ChatReaction.ReactionOnGoing) {
            if (event.getMessage().equalsIgnoreCase(ChatReaction.word)){
                long ms = System.currentTimeMillis() - ChatReaction.time;
                ChatReaction.ReactionOnGoing = false;
                double seconds = (ms/1000);
                double roundOff = Math.round(seconds*100)/100;
                for (Player cur : Bukkit.getOnlinePlayers()){
                    msg.sendP(cur, "&b" + event.getPlayer().getName() + " &ehas unscrambled the word &a" + ChatReaction.word + "&e in &f" + roundOff + " &eSeconds",true);
                }
            }
        }
    }
}


