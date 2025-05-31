package com.example.card2card.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_card_id")
    private Card sourceCard;

    @ManyToOne
    @JoinColumn(name = "destination_card_id")
    private Card destinationCard;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    public Transfer() {}

    public Transfer(Long id, Card sourceCard, Card destinationCard, BigDecimal amount,
                    LocalDateTime createdAt, TransferStatus status) {
        this.id = id;
        this.sourceCard = sourceCard;
        this.destinationCard = destinationCard;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Card getSourceCard() { return sourceCard; }

    public void setSourceCard(Card sourceCard) { this.sourceCard = sourceCard; }

    public Card getDestinationCard() { return destinationCard; }

    public void setDestinationCard(Card destinationCard) { this.destinationCard = destinationCard; }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public TransferStatus getStatus() { return status; }

    public void setStatus(TransferStatus status) { this.status = status; }
}
