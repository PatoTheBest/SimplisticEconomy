package net.megaplanet.simplisticeconomy.storage.types.mysql;

import net.megaplanet.simplisticeconomy.player.PlayerAccount;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.storage.StorageManager;
import net.megaplanet.simplisticeconomy.storage.TransactionResponse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MySQLStorage implements IStorage {

    private final StorageManager storageManager;
    private final MySQLConnectionHandler connectionHandler;
    private final Map<String, PlayerAccount> loadedPlayers = new HashMap<>();

    public MySQLStorage(StorageManager storageManager) {
        this.storageManager = storageManager;
        this.connectionHandler = new MySQLConnectionHandler(storageManager.getPlugin());
    }

    @Override
    public void loadAccount(String player) {
        connectionHandler.executeSQLQuery(connection -> {
            PreparedStatement getAccountStatement = connection.prepareStatement(Queries.SELECT);
            getAccountStatement.setString(1, player);
            ResultSet resultSet = getAccountStatement.executeQuery();

            double balance;

            if(resultSet.next()) {
                balance = resultSet.getDouble("balance");
            } else {
                balance = storageManager.getStartingBalance();
                PreparedStatement insertRecord = connection.prepareStatement(Queries.INSERT);
                insertRecord.setString(1, player);
                insertRecord.setDouble(2, balance);
                insertRecord.execute();
                insertRecord.close();
            }

            PlayerAccount playerAccount = new PlayerAccount(player, balance);
            loadedPlayers.put(player, playerAccount);

            resultSet.close();
            getAccountStatement.close();
        });
    }

    @Override
    public void unloadAccount(String player) {
        loadedPlayers.remove(player);
    }

    @Override
    public TransactionResponse depositPlayer(String player, double amount) {
        return null;
    }

    @Override
    public TransactionResponse withdrawPlayer(String player, double amount) {
        return null;
    }

    @Override
    public double getBalance(String player) {
        return 0;
    }

    @Override
    public boolean createAccount(String player) {
        return false;
    }

    @Override
    public boolean hasAccount(String player) {
        return false;
    }

    @Override
    public void enableStorage() {
        connectionHandler.setupHikari();
    }

    @Override
    public void disableStorage() {
        connectionHandler.closeHikari();
    }
}
