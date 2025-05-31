package com.example.card2card.dto.transfer;

import java.math.BigDecimal;

/**
 * DTO for transfer request data.
 * Holds source and destination card numbers and the transfer amount.
 */
public class TransferRequest {

    private String sourceCardNumber;
    private String destinationCardNumber;
    private BigDecimal amount;

    public TransferRequest() {}

    public String getSourceCardNumber() { return sourceCardNumber; }
    public void setSourceCardNumber(String sourceCardNumber) { this.sourceCardNumber = sourceCardNumber; }

    public String getDestinationCardNumber() { return destinationCardNumber; }
    public void setDestinationCardNumber(String destinationCardNumber) { this.destinationCardNumber = destinationCardNumber; }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
