package com.example.card2card.dto.card;

import java.math.BigDecimal;

/**
 * DTO class used to return the balance of a specific card to the client.
 */
public class CardBalanceResponse {

    private String cardNumber;
    private BigDecimal balance;

    public CardBalanceResponse() {}

    public CardBalanceResponse(String cardNumber, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
