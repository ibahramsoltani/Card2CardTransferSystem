package com.example.card2card.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(nullable = false)
    private BigDecimal transferLimit;

    public Card() {}

    public Card(Long id, String cardNumber, BigDecimal balance, User owner, Bank bank, BigDecimal transferLimit) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.owner = owner;
        this.bank = bank;
        this.transferLimit = transferLimit;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCardNumber() { return cardNumber; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getBalance() { return balance; }

    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public User getOwner() { return owner; }

    public void setOwner(User owner) { this.owner = owner; }

    public Bank getBank() { return bank; }

    public void setBank(Bank bank) { this.bank = bank; }

    public BigDecimal getTransferLimit() { return transferLimit; }

    public void setTransferLimit(BigDecimal transferLimit) { this.transferLimit = transferLimit; }
}
