package com.example.card2card.controller;


import com.example.card2card.dto.card.CardBalanceResponse;
import com.example.card2card.dto.card.CardResponse;
import com.example.card2card.dto.card.CreateCardRequest;
import com.example.card2card.dto.card.LimitUpdateRequest;
import com.example.card2card.security.JwtUtil;
import com.example.card2card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for handling card-related operations such as creating cards,
 * viewing balances, updating transfer limits, and listing user's cards.
 */
@RestController
@RequestMapping("/api/cards")
public class CardController {

    private CardService cardService;
    private JwtUtil jwtUtil;

    public CardController(CardService cardService, JwtUtil jwtUtil) {
        this.cardService = cardService;
        this.jwtUtil = jwtUtil;
    }

//    @PostMapping
//    public void addCard(@RequestBody CreateCardRequest request, Authentication authentication) {
//        String username = authentication.getName();
//        cardService.createCard(request, username);
//    }

    @PostMapping("/create")
    public ResponseEntity<String> createCard(@RequestBody CreateCardRequest request,
                                             @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        cardService.createCard(request, username);
        return ResponseEntity.ok(" کارت با موفقیت ایجاد شد.");
    }


    @GetMapping("/balance")
    public List<CardBalanceResponse> getCardBalances(Authentication authentication) {
        String username = authentication.getName();
        return cardService.getCardBalances(username);
    }

    @PutMapping("/{cardNumber}/limit")
    public ResponseEntity<String> updateTransferLimit(@PathVariable String cardNumber,
                                                      @RequestBody LimitUpdateRequest request,
                                                      @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        cardService.updateTransferLimit(cardNumber, request.getNewLimit(), username);
        return ResponseEntity.ok(" سقف انتقال با موفقیت بروزرسانی شد.");
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> getMyCards(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        List<CardResponse> cards = cardService.getCardsForUser(username);
        return ResponseEntity.ok(cards);
    }

}
