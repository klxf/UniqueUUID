package fang.uniqueuuid;

import org.bukkit.ChatColor;

public final class Util {
    public static String msgTranslate (String msg){
        msg = ChatColor.translateAlternateColorCodes('&', msg);
        return msg;
    }
}
