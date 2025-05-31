package com.example.card2card.service;

import com.example.card2card.dto.card.CardBalanceResponse;
import com.example.card2card.dto.card.CardResponse;
import com.example.card2card.dto.card.CreateCardRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CardService {

    void createCard(CreateCardRequest request, String username);
    List<CardResponse> getCardsForUser(String username);
    List<CardBalanceResponse> getCardBalances(String username);
    void updateTransferLimit(String cardNumber, BigDecimal newLimit, String username);

}
