package fang.uniqueuuid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public class CommandHandler implements CommandExecutor{

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FileConfiguration fc = UniqueUUID.instance.getConfig();

        if(strings.length == 0){
            UniqueUUID.instance.getLogger().info("请使用 /uniqueuuid help 查看命令帮助");
            return false;
        }
        String arg = strings[0];
        if(Objects.equals(arg, "reload")){
            UniqueUUID.instance.getLogger().info("开始重置插件...");
            UniqueUUID.reloadAll();
            return true;
        }else{
            return false;
        }
    }
}