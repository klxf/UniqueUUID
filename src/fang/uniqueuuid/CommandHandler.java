package fang.uniqueuuid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public class CommandHandler implements CommandExecutor{

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(strings.length == 0){
            commandSender.sendMessage("[UniqueUUID] " + Util.getMessages("command.error"));
            return false;
        }
        String arg = strings[0];
        if(Objects.equals(arg, "help")){
            commandSender.sendMessage("[UniqueUUID] " + Util.getMessages("command.helps"));
            return true;
        }else if (Objects.equals(arg, "reload")){
            commandSender.sendMessage("[UniqueUUID] " + Util.getMessages("reload.start"));
            UniqueUUID.reloadAll();
            return true;
        }else{
            return false;
        }
    }
}