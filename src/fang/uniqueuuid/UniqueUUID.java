package fang.uniqueuuid;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

import static fang.uniqueuuid.Util.getMessages;

public class UniqueUUID extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    public static JavaPlugin instance;
    public static boolean dbError = false;
    public static Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        logger = instance.getLogger();

        saveDefaultConfig();
        saveResource("messages.yml", false);

        logger.info("============ UniqueUUID ==============");
        logger.info(getMessages("plugin.enable"));

        Bukkit.getPluginCommand("uniqueuuid").setExecutor(new CommandHandler());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.warning(getMessages("database.jdbc_err"));
            //e.printStackTrace();
            dbError = true;
        }

        new DBDataManager().loadAll();

        logger.info("======================================");
    }

    public static void reloadAll() {
        instance.reloadConfig();
        new DBDataManager().loadAll();
        logger.info(getMessages("reload.success"));
    }

    @Override
    public void onDisable() {
        logger.info("============ UniqueUUID ==============");
        logger.info(getMessages("plugin.disable"));
        new DBDataManager().saveAll();
        logger.info("======================================");
    }
}
