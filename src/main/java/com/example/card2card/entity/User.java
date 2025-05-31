package com.example.card2card.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Card> cards;

    public User() {}

    public User(Long id, String username, String password, List<Card> cards) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cards = cards;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<Card> getCards() { return cards; }

    public void setCards(List<Card> cards) { this.cards = cards; }
}
