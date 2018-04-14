package net.megaplanet.simplisticeconomy.storage;

import java.util.Map;

public interface IStorage {

    void loadAccount(String player);

    void unloadAccount(String player);

    TransactionResponse depositPlayer(String player, double amount);

    TransactionResponse setPlayerBalance(String player, double amount);

    TransactionResponse withdrawPlayer(String player, double amount);

    default boolean hasEnough(String player, double amount) {
        return getBalance(player) >= amount;
    }

    double getBalance(String player);

    /*
       For vault
     */
    default boolean createAccount(String player) {
        getBalance(player);
        return true;
    }

    boolean hasAccount(String player);

    Map<String, Double> getTopBalance();

    void enableStorage();

    void disableStorage();

}
