package net.megaplanet.simplisticeconomy.storage.types;

import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.storage.TransactionResponse;

public class NullStorage implements IStorage {

    @Override
    public TransactionResponse loadAccount(String player) {
        return TransactionResponse.createFailureResponse("Unsupported storage");
    }

    @Override
    public TransactionResponse depositPlayer(String player, int amount) {
        return TransactionResponse.createFailureResponse("Unsupported storage");
    }

    @Override
    public TransactionResponse withdrawPlayer(String player, int amount) {
        return TransactionResponse.createFailureResponse("Unsupported storage");
    }

    @Override
    public boolean hasEnough(String player, int amount) {
        return false;
    }

    @Override
    public double getBalance(String player, int amount) {
        return 0;
    }
}
