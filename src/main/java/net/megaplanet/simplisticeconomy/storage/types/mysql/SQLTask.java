package net.megaplanet.simplisticeconomy.storage.types.mysql;

import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.SQLException;

class SQLTask extends BukkitRunnable {

    private final MySQLConnectionHandler mySQLConnectionHandler;
    private final SQLCallback sqlCallBack;

    SQLTask(MySQLConnectionHandler mySQLConnectionHandler, SQLCallback sqlCallBack) {
        this.mySQLConnectionHandler = mySQLConnectionHandler;
        this.sqlCallBack = sqlCallBack;
    }

    @Override
    public void run() {
        if(mySQLConnectionHandler.isClosed()) {
            return;
        }

        try (Connection connection = mySQLConnectionHandler.getConnection()){
            sqlCallBack.call(connection);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    SQLTask executeAsync() {
        runTaskAsynchronously(mySQLConnectionHandler.getPlugin());
        return this;
    }
}