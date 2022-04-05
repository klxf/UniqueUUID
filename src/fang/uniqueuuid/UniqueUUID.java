package fang.uniqueuuid;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class UniqueUUID extends JavaPlugin{
    @Override
    public void onLoad() {

    }

    public static JavaPlugin instance;
    public static boolean dbError = false;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "启动插件...");
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            getLogger().log(Level.WARNING, "数据库驱动加载失败，将关闭插件。");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
            dbError = true;
        }
        saveDefaultConfig();
        instance = this;

        new DBDataManager().loadAll();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "关闭插件...");
        new DBDataManager().saveAll();
    }
}
