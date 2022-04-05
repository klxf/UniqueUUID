package fang.uniqueuuid;

import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nonnull;
import java.sql.*;

public class DBDataManager implements IDataManager{
    static String db_url;
    static String host;
    static String port;
    static String username;
    static String password;
    static String db_name;

    @Override
    public void saveAll() {

    }

    @Override
    public void loadAll(){
        FileConfiguration fc = UniqueUUID.instance.getConfig();
        host = fc.getString("mysql.host");
        port = fc.getString("mysql.port");
        username = fc.getString("mysql.username");
        db_name = fc.getString("mysql.dbname");
        password = fc.getString("mysql.password");

        if(host == null || port == null || username == null || db_name == null || password == null){
            UniqueUUID.dbError = true;
            UniqueUUID.instance.getLogger().warning("数据库配置不完全，将关闭插件。");
            return;
        }

        db_url = "jdbc:mysql://" + host + ":" + port + "/" + db_name + "?useSSL=false&allowPublicKeyRetrieval=true&ServerTimezone=UTC";

        try {
            Connection connection = DriverManager.getConnection(db_url, username, password);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS player_uuid_data( `id` INT NOT NULL AUTO_INCREMENT , `username` TEXT NOT NULL , `UUID` TEXT NOT NULL , PRIMARY KEY (`id`));");
            statement.close();
            connection.close();
            UniqueUUID.instance.getLogger().info("成功连接数据库！");
        } catch (SQLException e) {
            putError(e);
        }
    }

    private void putError(Exception e) {
        UniqueUUID.dbError = true;
        UniqueUUID.instance.getLogger().warning("SQL语句裂开了！");
        e.printStackTrace();
    }

    @Nonnull
    public String getPlayerUUID (String id) {
        try {
            Connection connection = DriverManager.getConnection(db_url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT UUID FROM player_uuid_data WHERE username=?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            String playerUUID = null;
            if (rs.first()) {
                playerUUID = rs.getString("UUID");
            }
            preparedStatement.close();
            connection.close();
            return playerUUID;
        } catch (SQLException e) {
            putError(e);
            return null;
        }
    }


    public void addPlayerUUID(String id, String playerUUID) {
        try {
            Connection connection = DriverManager.getConnection(db_url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player_uuid_data(username, UUID) VALUES (?, ?);");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, playerUUID);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            putError(e);
        }
    }
}