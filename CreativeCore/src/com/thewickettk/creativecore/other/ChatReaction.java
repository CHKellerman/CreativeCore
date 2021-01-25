package com.thewickettk.creativecore.other;

import com.thewickettk.creativecore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ChatReaction {
    public static ArrayList<String> words = new ArrayList<String>();
    public static boolean ReactionOnGoing = false;
    public static String word;
    public static String scrambleWord;
    public static long time = 0;


    public static void chatReactionStart(Boolean scramble){
        if (!scramble){
            Random rand = new Random();
            int random = rand.nextInt(words.size());
            word = words.get(random);
            scrambleWord = word;
            time = System.currentTimeMillis();
            for (Player cur : Bukkit.getServer().getOnlinePlayers()){
                msg.sendP(cur, "&eType this word: " + scrambleWord,true);
            }
            ReactionOnGoing = true;
            return;
        }
            Random rand = new Random();
            int random = rand.nextInt(words.size());
            word = words.get(random);
            List<Character> arr = new ArrayList<>();
            for(char c :  word.toCharArray()) //for each char of the word selectionned, put it in a list
                arr.add(c);
            Collections.shuffle(arr); //shuffle the list
            StringBuilder sb = new StringBuilder(); //now rebuild the word
            for(char c : arr)
                sb.append(c);
            scrambleWord = sb.toString();
            time = System.currentTimeMillis();
            for (Player cur : Bukkit.getServer().getOnlinePlayers()){
                msg.sendP(cur, "&eUnscramble the word: " + scrambleWord,true);
            }
            ReactionOnGoing = true;
    }

    public static void chatReactionExspired(){
        if (ChatReaction.ReactionOnGoing){
            for (Player cur : Bukkit.getServer().getOnlinePlayers()){
                msg.sendP(cur, "&eUnscramble has expired, better luck next time",true);
            }
            ReactionOnGoing = false;
        }
    }

    public static void getWordlist(){
        for (String cur: Main.config.getStringList("ReactionWords")){
            words.add(cur);
        }
    }

    public static int getOnlineTotal(){
        int o = 0;
        for (Player Cur : Bukkit.getServer().getOnlinePlayers()){
            o++;
        }
        return o;
    }
}
