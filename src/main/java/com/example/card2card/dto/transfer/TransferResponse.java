package com.example.card2card.dto.transfer;

import java.math.BigDecimal;

/**
 * DTO representing the response after a transfer operation.
 * Contains destination card holder name, card number, and amount transferred.
 */
public class TransferResponse {
    private String destinationCardHolder;
    private String destinationCardNumber;
    private BigDecimal transferredAmount;

    public TransferResponse(String holder, String cardNumber, BigDecimal amount) {
        this.destinationCardHolder = holder;
        this.destinationCardNumber = cardNumber;
        this.transferredAmount = amount;
    }

    public String getDestinationCardHolder() {
        return destinationCardHolder;
    }

    public void setDestinationCardHolder(String destinationCardHolder) {
        this.destinationCardHolder = destinationCardHolder;
    }

    public String getDestinationCardNumber() {
        return destinationCardNumber;
    }

    public void setDestinationCardNumber(String destinationCardNumber) {
        this.destinationCardNumber = destinationCardNumber;
    }

    public BigDecimal getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(BigDecimal transferredAmount) {
        this.transferredAmount = transferredAmount;
    }
}
