package fang.uniqueuuid;

import org.bukkit.ChatColor;

import javax.annotation.Nonnull;
import java.util.UUID;

public final class Util {
    @Nonnull
    public static String msgTranslate (@Nonnull String msg, String playername, UUID playeruuid){
        msg = msg.replace("{player}", playername);
        msg = msg.replace("{uuid}", playeruuid.toString());
        msg = ChatColor.translateAlternateColorCodes('&', msg);
        return msg;
    }
}
