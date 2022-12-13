package fang.uniqueuuid;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Objects;
import java.util.UUID;

public class EventListener implements Listener{

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        IDataManager idm;
        idm = new DBDataManager();
        String playername = e.getPlayer().getName().toLowerCase();
        UUID plaueruuid = e.getPlayer().getUniqueId();
        String db_playeruuid = idm.getPlayerUUID(playername);

        if(db_playeruuid == null){
            idm.addPlayerUUID(playername, plaueruuid.toString());
        }else if(db_playeruuid != null && !db_playeruuid.equals(plaueruuid.toString())){
            UniqueUUID.logger.warning(Objects.requireNonNull(Util.getMessages("tips.console_tip")).replace("{player}", e.getPlayer().getName()).replace("{uuid}", e.getPlayer().getUniqueId().toString()));
            UniqueUUID.logger.warning("playerName:" + e.getPlayer().getName() + ", nowUUID:" + e.getPlayer().getUniqueId() + ", oldUUID:" + db_playeruuid);
            e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            e.disallow(e.getResult(), "\n" + Objects.requireNonNull(Util.getMessages("tips.kick_tip")).replace("{player}", e.getPlayer().getName()).replace("{uuid}", e.getPlayer().getUniqueId().toString()));
        }
    }
}