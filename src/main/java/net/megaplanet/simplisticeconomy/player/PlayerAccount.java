package net.megaplanet.simplisticeconomy.player;

import java.util.UUID;

/**
 * The class responsible for storing the player's account information
 */
public class PlayerAccount {

    private PlayerIdentity accountHolder;
    private int balance;

    public PlayerAccount(int balance) {
        this.balance = balance;
    }

    /**
     * Gets the holder of this account
     *
     * @return the account's holder
     */
    public PlayerIdentity getAccountHolder() {
        return accountHolder;
    }

    /**
     * Gets the account's balance
     *
     * @return the account's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the account's balance
     * <p>
     * Must be used exclusively by the storage implementation
     *
     * @param balance the new balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
