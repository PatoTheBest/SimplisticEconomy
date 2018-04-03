package net.megaplanet.simplisticeconomy.storage.types.mysql;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import net.megaplanet.simplisticeconomy.SimplisticEconomy;
import net.megaplanet.simplisticeconomy.files.ConfigFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQLConnectionHandler {

    private final SimplisticEconomy plugin;
    private final ConfigFile coreConfig;
    private HikariDataSource hikari;

    public MySQLConnectionHandler(SimplisticEconomy plugin) {
        this.coreConfig = plugin.getFileManager().getConfigFile();
        this.plugin = plugin;
    }

    public void setupHikari() {
        hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(8);
        hikari.setPoolName("SimplisticEconomy Connection Pool");
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", coreConfig.getString("storage.host"));
        hikari.addDataSourceProperty("port", coreConfig.getString("storage.port"));
        hikari.addDataSourceProperty("databaseName", coreConfig.getString("storage.database"));
        hikari.addDataSourceProperty("user", coreConfig.getString("storage.username"));
        hikari.addDataSourceProperty("password", coreConfig.getString("storage.password"));

        plugin.getLogger().log(Level.INFO, "Attempting to connect to database...");
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(Queries.CREATE_TABLE.replace("%TABLE%", ((MySQLStorage)plugin.getStorageManager().getStorage()).getTableName()));
            statement.execute();
            statement.close();
            plugin.getLogger().log(Level.INFO, "Successfully connected to mysql database");
        } catch (SQLException | HikariPool.PoolInitializationException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to connected to mysql database");
            e.printStackTrace();
        }
    }

    public void closeHikari() {
        hikari.close();
    }

    public boolean isClosed() {
        return hikari.isClosed();
    }

    public Connection getConnection() throws SQLException {
        return hikari != null ? hikari.getConnection() : null;
    }

    public void executeSQLQuery(SQLCallback callback) {
        executeSQLQuery(callback, false);
    }

    public void executeSQLQuery(SQLCallback callback, boolean async) {
        SQLTask task = new SQLTask(this, callback);

        if(async) {
            task.executeAsync();
        } else {
            task.run();
        }
    }

    public SimplisticEconomy getPlugin() {
        return plugin;
    }
}