package fang.uniqueuuid;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class Util {
    public static String msgTranslate (String msg){
        msg = ChatColor.translateAlternateColorCodes('&', msg);
        return msg;
    }

    public static String getMessages (String m){
        File msgFile = new File(UniqueUUID.instance.getDataFolder(), "messages.yml");
        FileConfiguration messages = YamlConfiguration.loadConfiguration(msgFile);
        String msg = msgTranslate(messages.getString(m));
        return msg;
    }
}