package net.megaplanet.simplisticeconomy.storage;

import com.sun.istack.internal.Nullable;

public class TransactionResponse {

    private final double amount;
    private final double balance;
    private final TransactionResponseType transactionResponseType;
    private String failureReason;

    public static TransactionResponse createSuccessResponse(double amount, double balance) {
        return new TransactionResponse(TransactionResponseType.SUCCESS, amount, balance);
    }

    public static TransactionResponse createFailureResponse(String failureReason, double amount, double balance) {
        return new TransactionResponse(TransactionResponseType.FAILURE, failureReason, amount, balance);
    }

    private TransactionResponse(TransactionResponseType transactionResponseType, double amount, double balance) {
        this.transactionResponseType = transactionResponseType;
        this.amount = amount;
        this.balance = balance;
    }

    /* protected for builder methods */
    TransactionResponse(TransactionResponseType transactionResponseType, String failureReason, double amount, double balance) {
        this.transactionResponseType = transactionResponseType;
        this.failureReason = failureReason;
        this.amount = amount;
        this.balance = balance;
    }

    /**
     * Gets the transaction response type
     *
     * @return the transaction response type
     */
    public TransactionResponseType getTransactionResponseType() {
        return transactionResponseType;
    }

    /**
     * Gets the failure reason if the transaction was a failure
     * <p>
     * Will return null if the {@link TransactionResponseType} is
     * {@link TransactionResponseType#SUCCESS}
     *
     * @return the failure reason
     */
    public @Nullable String getFailureReason() {
        return failureReason;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }
}
