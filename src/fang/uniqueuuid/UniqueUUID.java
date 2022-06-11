package fang.uniqueuuid;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.Objects;
import java.util.logging.Level;

public class UniqueUUID extends JavaPlugin{
    @Override
    public void onLoad() {

    }

    public static JavaPlugin instance;
    public static boolean dbError = false;
    @Override
    public void onEnable() {
        getLogger().info("============ UniqueUUID ==============");
        getLogger().info("插件启动中...");
        Bukkit.getPluginCommand("uniqueuuid").setExecutor(new CommandHandler());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            getLogger().log(Level.WARNING, "数据库驱动加载失败，将关闭插件。");
            //e.printStackTrace();
            //Bukkit.getPluginManager().disablePlugin(this);
            dbError = true;
        }
        saveDefaultConfig();
        instance = this;

        new DBDataManager().loadAll();
        getLogger().info("======================================");
    }

    public static void reloadAll() {
        instance.reloadConfig();
        new DBDataManager().loadAll();
        instance.getLogger().info(instance.getConfig().getString("messages.reload"));
    }

    @Override
    public void onDisable() {
        getLogger().info("============ UniqueUUID ==============");
        getLogger().log(Level.INFO, "关闭插件...");
        new DBDataManager().saveAll();
        getLogger().info("======================================");
    }
}
