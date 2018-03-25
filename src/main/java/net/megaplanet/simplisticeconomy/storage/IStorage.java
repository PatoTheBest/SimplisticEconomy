package net.megaplanet.simplisticeconomy.storage;

public interface IStorage {

    void loadAccount(String player);

    void depositPlayer(String player, int amount);

    void withdrawPlayer(String player, int amount);

    void hasEnough(String player, int amount);

    void getBalance(String player, int amount);

}
