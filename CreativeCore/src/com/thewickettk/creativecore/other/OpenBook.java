package com.thewickettk.creativecore.other;

import com.thewickettk.creativecore.Main;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class OpenBook {
   // plot auto - Use this command to claim a random empty plot in the plot world!


    public static ItemStack createBook(Player player){
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setTitle(msg.back(Main.config.getString("SpawnBook.Title"),false));
        bookMeta.setAuthor(msg.back(Main.config.getString("SpawnBook.Author"),false));
        ArrayList<String> s = new ArrayList<>();
        for (String cur: Main.config.getStringList("SpawnBook.pages")){
            cur = cur.replace("{Player}", player.getName());
            cur = cur.replace("{enter}", System.lineSeparator());
            s.add(ChatColor.translateAlternateColorCodes('&', cur));
        }
        bookMeta.setPages(s);
        book.setItemMeta(bookMeta);
        return book;
    }


    public static void openBook(ItemStack book, Player p) {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);

        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, (byte)0);
        buf.writerIndex(1);

        try {
            Constructor<?> serializerConstructor = NMSUtils.getNMSClass("PacketDataSerializer").getConstructor(ByteBuf.class);
            Object packetDataSerializer = serializerConstructor.newInstance(buf);

            Constructor<?> keyConstructor = NMSUtils.getNMSClass("MinecraftKey").getConstructor(String.class);
            Object bookKey = keyConstructor.newInstance("minecraft:book_open");

            Constructor<?> titleConstructor = NMSUtils.getNMSClass("PacketPlayOutCustomPayload").getConstructor(bookKey.getClass(), NMSUtils.getNMSClass("PacketDataSerializer"));
            Object payload = titleConstructor.newInstance(bookKey, packetDataSerializer);

            NMSUtils.sendPacket(p, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        p.getInventory().setItem(slot, old);
    }
}

