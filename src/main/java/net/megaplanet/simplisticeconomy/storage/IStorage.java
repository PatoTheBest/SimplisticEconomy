package net.megaplanet.simplisticeconomy.storage;

public interface IStorage {

    void loadAccount(String player);

    void unloadAccount(String player);

    TransactionResponse depositPlayer(String player, double amount);

    TransactionResponse withdrawPlayer(String player, double amount);

    boolean hasEnough(String player, double amount);

    double getBalance(String player);

    boolean createAccount(String player);

    boolean hasAccount(String player);

}
