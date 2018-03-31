package net.megaplanet.simplisticeconomy.player;

/**
 * The class responsible for storing the player's account information
 */
public class PlayerAccount {

    private final String accountHolder;
    private double balance;

    public PlayerAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    /**
     * Gets the holder of this account
     *
     * @return the account's holder
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * Gets the account's balance
     *
     * @return the account's balance
     */
    public double getBalance() {
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
