package net.megaplanet.simplisticeconomy.storage.types;

import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.storage.TransactionResponse;

public class NullStorage implements IStorage {

    @Override
    public void loadAccount(String player) {

    }

    @Override
    public void unloadAccount(String player) {

    }

    @Override
    public TransactionResponse depositPlayer(String player, double amount) {
        return TransactionResponse.createFailureResponse("Unsupported storage", 0, 0);
    }

    @Override
    public TransactionResponse withdrawPlayer(String player, double amount) {
        return TransactionResponse.createFailureResponse("Unsupported storage", 0, 0);
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

    @Override
    public void enableStorage() {

    }

    @Override
    public void disableStorage() {

    }
}
