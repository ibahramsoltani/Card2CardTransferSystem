package com.example.card2card.dto.card;


import java.math.BigDecimal;

/**
 * DTO for creating a new card.
 * Contains card number, bank ID, initial balance, and transfer limit.
 */
public class CreateCardRequest {
    private String cardNumber;
    private Long bankId;
    private BigDecimal initialBalance;
    private BigDecimal transferLimit;

    public CreateCardRequest() {
    }

    public CreateCardRequest(String cardNumber, Long bankId,BigDecimal initialBalance, BigDecimal transferLimit) {
        this.cardNumber = cardNumber;
        this.bankId = bankId;
        this.initialBalance = initialBalance;
        this.transferLimit = transferLimit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(BigDecimal transferLimit) {
        this.transferLimit = transferLimit;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}
