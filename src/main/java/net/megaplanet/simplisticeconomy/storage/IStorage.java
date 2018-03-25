package net.megaplanet.simplisticeconomy.storage;

public interface IStorage {

    TransactionResponse loadAccount(String player);

    TransactionResponse depositPlayer(String player, int amount);

    TransactionResponse withdrawPlayer(String player, int amount);

    boolean hasEnough(String player, int amount);

    double getBalance(String player, int amount);

}
