package net.megaplanet.simplisticeconomy.storage.types.mysql;

import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.storage.StorageManager;
import net.megaplanet.simplisticeconomy.storage.TransactionResponse;

public class MySQLStorage implements IStorage {

    private final StorageManager storageManager;

    public MySQLStorage(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void loadAccount(String player) {

    }

    @Override
    public void unloadAccount(String player) {

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
    public boolean hasEnough(String player, double amount) {
        return false;
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
}
