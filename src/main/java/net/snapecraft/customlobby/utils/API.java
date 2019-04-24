package net.snapecraft.customlobby.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginDescriptionFile;

public class API
{
    public static String getPrefix()
    {
        return CustomLobby.getPrefix();
    }

    public static String getPluginVersion()
    {
        return CustomLobby.getInstance().getDescription().getVersion();
    }

    public static String getPluginDescription()
    {
        return CustomLobby.getInstance().getDescription().getDescription();
    }

    public static List<String> getPluginAuthor()
    {
        return CustomLobby.getInstance().getDescription().getAuthors();
    }

    public static String getPluginName()
    {
        return CustomLobby.getInstance().getDescription().getFullName();
    }

    public static PluginDescriptionFile getPluginDescriptionObject() {
        return CustomLobby.getInstance().getDescription();
    }

    public static boolean isInt(String s)
    {
        try
        {
            Integer.parseInt(s);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static String getNoPermString()
    {
        return CustomLobby.noPermission;
    }

    public static String toString(boolean b, String trueReturn, String falseReturn)
    {
        if (b) {
            return trueReturn;
        }
        return falseReturn;
    }

    public static Material toMaterial(String material)
    {
        return Material.getMaterial(material);
    }

    public static String getNameOfCorrespondingNavSlot(Integer slot) {
        for(String warp : CustomLobby.getInstance().getConfig().getConfigurationSection("warp").getKeys(false)) {
            if(CustomLobby.getInstance().getConfig().getConfigurationSection(warp).getInt("slot") == slot) {
                return warp;
            }
        }

        return "";
    }

    public static ConfigurationSection getCorrespondingConfigSection(String name) {
        for(String warp : CustomLobby.getInstance().getConfig().getConfigurationSection("warp").getKeys(false)) {
            if(warp.equals(name)) {
                return CustomLobby.getInstance().getConfig().getConfigurationSection(warp);
            }
        }
        return null;
    }
    public static boolean reverseBoolean(boolean in) {
        return !in;
    }
    public static String randomSkull() {
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        List<String> heads = CustomLobby.getInstance().getConfig().getStringList("headlist");
        Random random = new Random();
        return heads.get(random.nextInt(heads.size()));
    }

    @SuppressWarnings("Duplicates")
    public static Material randomBoot() {
        List<Material> boots = new ArrayList<>();
        boots.add(Material.LEATHER_BOOTS);
        boots.add(Material.CHAINMAIL_BOOTS);
        boots.add(Material.GOLD_BOOTS);
        boots.add(Material.IRON_BOOTS);
        boots.add(Material.DIAMOND_BOOTS);
        Random random = new Random();
        return  boots.get(random.nextInt(boots.size()));
    }

    @SuppressWarnings("Duplicates")
    public static List<Material> getBoots() {
        List<Material> boots = new ArrayList<>();
        boots.add(Material.LEATHER_BOOTS);
        boots.add(Material.CHAINMAIL_BOOTS);
        boots.add(Material.GOLD_BOOTS);
        boots.add(Material.IRON_BOOTS);
        boots.add(Material.DIAMOND_BOOTS);
        return boots;
    }
}
