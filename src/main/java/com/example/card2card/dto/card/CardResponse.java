package com.example.card2card.dto.card;

import java.math.BigDecimal;


/**
 * DTO class used to return card details such as balance, transfer limit, and bank name to the client.
 */
public class CardResponse {

    private String cardNumber;
    private BigDecimal balance;
    private BigDecimal transferLimit;
    private String bankName;

    public CardResponse() {
    }

    public CardResponse(String cardNumber, BigDecimal balance, BigDecimal transferLimit, String bankName) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.transferLimit = transferLimit;
        this.bankName = bankName;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(BigDecimal transferLimit) {
        this.transferLimit = transferLimit;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
