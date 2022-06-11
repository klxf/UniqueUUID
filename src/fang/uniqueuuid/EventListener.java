package fang.uniqueuuid;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;
import java.util.UUID;

public class EventListener implements Listener{

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        IDataManager idm;
        FileConfiguration fc = UniqueUUID.instance.getConfig();
        idm = new DBDataManager();
        String playername = e.getPlayer().getName().toLowerCase();
        UUID plaueruuid = e.getPlayer().getUniqueId();
        String db_playeruuid = idm.getPlayerUUID(playername);

        if(db_playeruuid == null){
            idm.addPlayerUUID(playername, plaueruuid.toString());
        }else if(db_playeruuid != null && !db_playeruuid.equals(plaueruuid.toString())){
            UniqueUUID.instance.getLogger().warning(Util.msgTranslate(Objects.requireNonNull(fc.getString("messages.console_tip")).replace("{player}", e.getPlayer().getName()).replace("{uuid}", e.getPlayer().getUniqueId().toString())));
            UniqueUUID.instance.getLogger().warning("playerName:" + e.getPlayer().getName() + ", nowUUID:" + e.getPlayer().getUniqueId() + ", oldUUID:" + db_playeruuid);
            e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            e.disallow(e.getResult(), "\n" + Util.msgTranslate(Objects.requireNonNull(fc.getString("messages.kick_tip")).replace("{player}", e.getPlayer().getName()).replace("{uuid}", e.getPlayer().getUniqueId().toString())));
        }
    }
}