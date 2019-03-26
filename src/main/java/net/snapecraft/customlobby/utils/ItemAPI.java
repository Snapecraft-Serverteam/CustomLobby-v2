package net.snapecraft.customlobby.utils;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemAPI
{
    public static ItemStack createItem(Material material, String displayname, byte bite, Integer amount)
    {
        ItemStack item = new ItemStack(material, amount, (short)bite);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemWithLore(Material material, String displayname, byte bite, Integer amount, List<String> lore)
    {
        ItemStack item = new ItemStack(material, amount, (short)bite);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createSkull(String skullOwner, String displayname, Integer amount)
    {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (short)3);
        SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setOwner(skullOwner);
        skull.setItemMeta(meta);
        return skull;
    }

    public static ItemStack createSkullWithLore(String skullOwner, String displayname, Integer amount, List<String> lore)
    {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (short)3);
        SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setOwner(skullOwner);
        meta.setLore(lore);
        skull.setItemMeta(meta);
        return skull;
    }
}
