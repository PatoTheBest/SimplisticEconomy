package net.megaplanet.simplisticeconomy.storage.types.mysql;

import net.megaplanet.simplisticeconomy.player.PlayerAccount;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.storage.StorageManager;
import net.megaplanet.simplisticeconomy.storage.TransactionResponse;
import net.megaplanet.simplisticeconomy.storage.TransactionResponseBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class MySQLStorage implements IStorage {

    private final StorageManager storageManager;
    private final MySQLConnectionHandler connectionHandler;
    private final Map<String, PlayerAccount> loadedPlayers = new HashMap<>();
    private String tableName;

    public MySQLStorage(StorageManager storageManager) {
        this.storageManager = storageManager;
        this.connectionHandler = new MySQLConnectionHandler(storageManager.getPlugin());
        this.tableName = storageManager.getPlugin().getFileManager().getConfigFile().getString("storage.table");
    }

    @Override
    public void loadAccount(String player) {
        connectionHandler.executeSQLQuery(connection -> {
            PlayerAccount playerAccount = loadAccount(player, connection);
            loadedPlayers.put(player, playerAccount);
        });
    }

    private PlayerAccount loadAccount(String player, Connection connection) throws SQLException {
        PreparedStatement getAccountStatement = connection.prepareStatement(replaceTable(Queries.SELECT));
        getAccountStatement.setString(1, player);
        ResultSet resultSet = getAccountStatement.executeQuery();

        double balance;

        if(resultSet.next()) {
            balance = resultSet.getDouble("balance");
        } else {
            balance = storageManager.getStartingBalance();
            PreparedStatement insertRecord = connection.prepareStatement(replaceTable(Queries.INSERT));
            insertRecord.setString(1, player);
            insertRecord.setDouble(2, balance);
            insertRecord.execute();
            insertRecord.close();
        }

        resultSet.close();
        getAccountStatement.close();
        return new PlayerAccount(player, balance);
    }

    @Override
    public void unloadAccount(String player) {
        loadedPlayers.remove(player);
    }

    @Override
    public TransactionResponse depositPlayer(String player, double amount) {
        double initialBalance = getBalance(player);

        if(loadedPlayers.containsKey(player)) {
            loadedPlayers.get(player).setBalance(initialBalance + amount);
        }

        connectionHandler.executeSQLQuery(connection -> {
            PreparedStatement updateBalance = connection.prepareStatement(replaceTable(Queries.UPDATE_ADD));
            updateBalance.setDouble(1, amount);
            updateBalance.setString(2, player);
            updateBalance.execute();
            updateBalance.close();
        }, loadedPlayers.containsKey(player));

        return TransactionResponse.createSuccessResponse(amount, initialBalance + amount);
    }

    @Override
    public TransactionResponse setPlayerBalance(String player, double amount) {
        if(loadedPlayers.containsKey(player)) {
            loadedPlayers.get(player).setBalance(amount);
        }

        connectionHandler.executeSQLQuery(connection -> {
            PreparedStatement updateBalance = connection.prepareStatement(replaceTable(Queries.UPDATE_SET));
            updateBalance.setDouble(1, amount);
            updateBalance.setString(2, player);
            updateBalance.execute();
            updateBalance.close();
        }, loadedPlayers.containsKey(player));

        return TransactionResponse.createSuccessResponse(amount, amount);
    }

    @Override
    public TransactionResponse withdrawPlayer(String player, double amount) {
        TransactionResponse transactionResponse;
        double initialBalance = getBalance(player);

        if(initialBalance < amount) {
            transactionResponse = TransactionResponse.createFailureResponse("Insufficient funds", amount, initialBalance);
        } else {
            double newBalance = initialBalance - amount;
            if(loadedPlayers.containsKey(player)) {
                loadedPlayers.get(player).setBalance(newBalance);
            }

            connectionHandler.executeSQLQuery(connection -> {
                PreparedStatement updateBalance = connection.prepareStatement(replaceTable(Queries.UPDATE_REMOVE));
                updateBalance.setDouble(1, amount);
                updateBalance.setString(2, player);
                updateBalance.execute();
                updateBalance.close();
            }, loadedPlayers.containsKey(player));
            transactionResponse = TransactionResponse.createSuccessResponse(amount, newBalance);
        }

        return transactionResponse;
    }

    @Override
    public double getBalance(String player) {
        AtomicReference<PlayerAccount> playerAccount = new AtomicReference<>(loadedPlayers.get(player));

        if(playerAccount.get() == null) {
            connectionHandler.executeSQLQuery(connection -> playerAccount.set(loadAccount(player, connection)));
        }

        return playerAccount.get().getBalance();
    }

    @Override
    public boolean hasAccount(String player) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        if(loadedPlayers.containsKey(player)) {
            return true;
        }

        connectionHandler.executeSQLQuery(connection -> {
            PreparedStatement getAccountStatement = connection.prepareStatement(replaceTable(Queries.SELECT));
            getAccountStatement.setString(1, player);
            ResultSet resultSet = getAccountStatement.executeQuery();

            if(resultSet.next()) {
                atomicBoolean.set(true);
            }

            resultSet.close();
            getAccountStatement.close();
        });

        return atomicBoolean.get();
    }

    private String replaceTable(String statement) {
        return statement.replace("%TABLE%", tableName);
    }

    @Override
    public void enableStorage() {
        connectionHandler.setupHikari();
    }

    @Override
    public void disableStorage() {
        connectionHandler.closeHikari();
    }

    public String getTableName() {
        return tableName;
    }
}
