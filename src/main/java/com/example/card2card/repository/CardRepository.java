package com.example.card2card.repository;

import com.example.card2card.entity.Card;
import com.example.card2card.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardNumber(String cardNumber);
    List<Card> findByOwner(User owner);
}
