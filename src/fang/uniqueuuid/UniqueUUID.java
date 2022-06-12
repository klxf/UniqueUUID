package fang.uniqueuuid;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import static fang.uniqueuuid.Util.getMessages;

public class UniqueUUID extends JavaPlugin{
    @Override
    public void onLoad() {

    }

    public static JavaPlugin instance;
    public static boolean dbError = false;
    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("messages.yml", false);

        getLogger().info("============ UniqueUUID ==============");
        getLogger().info(getMessages("plugin.enable"));

        Bukkit.getPluginCommand("uniqueuuid").setExecutor(new CommandHandler());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            getLogger().warning(getMessages("database.jdbc_err"));
            //e.printStackTrace();
            dbError = true;
        }

        new DBDataManager().loadAll();

        getLogger().info("======================================");
    }

    public static void reloadAll() {
        instance.reloadConfig();
        new DBDataManager().loadAll();
        instance.getLogger().info(getMessages("reload.success"));
    }

    @Override
    public void onDisable() {
        getLogger().info("============ UniqueUUID ==============");
        getLogger().info(getMessages("plugin.disable"));
        new DBDataManager().saveAll();
        getLogger().info("======================================");
    }
}
