package net.megaplanet.simplisticeconomy.storage;

public final class TransactionResponseBuilder {

    private double amount;
    private double balance;
    private TransactionResponseType transactionResponseType;
    private String failureReason;

    private TransactionResponseBuilder() {
    }

    public static TransactionResponseBuilder createTransactionResponse() {
        return new TransactionResponseBuilder();
    }

    public TransactionResponseBuilder amount(double amount) {
        this.amount = amount;
        return this;
    }

    public TransactionResponseBuilder balance(double balance) {
        this.balance = balance;
        return this;
    }

    public TransactionResponseBuilder transactionResponseType(TransactionResponseType transactionResponseType) {
        this.transactionResponseType = transactionResponseType;
        return this;
    }

    public TransactionResponseBuilder failureReason(String failureReason) {
        this.failureReason = failureReason;
        return this;
    }

    public TransactionResponse build() {
        return new TransactionResponse(transactionResponseType, failureReason, amount, balance);
    }
}
