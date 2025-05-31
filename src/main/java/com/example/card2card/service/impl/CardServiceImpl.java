package com.example.card2card.service.impl;

import com.example.card2card.dto.card.CardBalanceResponse;
import com.example.card2card.dto.card.CardResponse;
import com.example.card2card.dto.card.CreateCardRequest;
import com.example.card2card.entity.Bank;
import com.example.card2card.entity.Card;
import com.example.card2card.entity.User;
import com.example.card2card.repository.BankRepository;
import com.example.card2card.repository.CardRepository;
import com.example.card2card.repository.UserRepository;
import com.example.card2card.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankRepository bankRepository;

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);


    @Override
    public void createCard(CreateCardRequest request, String username) {

        if (cardRepository.findByCardNumber(request.getCardNumber()).isPresent()) {
            throw new RuntimeException("شماره کارت تکراری است");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bank bank = bankRepository.findById(request.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        Card card = new Card();
        card.setCardNumber(request.getCardNumber());
        card.setBalance(request.getInitialBalance());
        card.setTransferLimit(request.getTransferLimit());
        card.setOwner(user);
        card.setBank(bank);

        cardRepository.save(card);
    }


    @Override
    public List<CardBalanceResponse> getCardBalances(String username) {
        logger.info("درخواست موجودی کارت برای کاربر: {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Card> cards = cardRepository.findByOwner(user);
        List<CardBalanceResponse> responses = new ArrayList<>();

        for (Card card : cards) {
            logger.info("کارت: {}, موجودی: {}", card.getCardNumber(), card.getBalance());

            responses.add(new CardBalanceResponse(
                    card.getCardNumber(),
                    card.getBalance()
            ));
        }

        return responses;
    }


    @Override
    public List<CardResponse> getCardsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("کاربر یافت نشد"));

        return cardRepository.findByOwner(user)
                .stream()
                .map(card -> {
                    CardResponse dto = new CardResponse();
                    dto.setCardNumber(card.getCardNumber());
                    dto.setBalance(card.getBalance());
                    dto.setTransferLimit(card.getTransferLimit());
                    dto.setBankName(card.getBank().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void updateTransferLimit(String cardNumber, BigDecimal newLimit, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("کاربر یافت نشد"));

        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("کارت یافت نشد"));

        if (!card.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("شما مجاز به تغییر این کارت نیستید.");
        }

        card.setTransferLimit(newLimit);
        cardRepository.save(card);
    }

}
