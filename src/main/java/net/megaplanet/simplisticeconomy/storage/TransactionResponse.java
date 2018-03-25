package net.megaplanet.simplisticeconomy.storage;

import com.sun.istack.internal.Nullable;

public class TransactionResponse {

    private final TransactionResponseType transactionResponseType;
    private String failureReason;

    public static TransactionResponse createSuccessResponse() {
        return new TransactionResponse(TransactionResponseType.SUCCESS);
    }

    public static TransactionResponse createFailureResponse(String failureReason) {
        return new TransactionResponse(TransactionResponseType.FAILURE, failureReason);
    }

    private TransactionResponse(TransactionResponseType transactionResponseType) {
        this.transactionResponseType = transactionResponseType;
    }

    /* private for builder methods */
    private TransactionResponse(TransactionResponseType transactionResponseType, String failureReason) {
        this.transactionResponseType = transactionResponseType;
        this.failureReason = failureReason;
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
}
