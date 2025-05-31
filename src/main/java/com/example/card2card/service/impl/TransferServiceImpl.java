package com.example.card2card.service.impl;

import com.example.card2card.dto.transfer.TransferRequest;
import com.example.card2card.dto.transfer.TransferResponse;
import com.example.card2card.entity.Card;
import com.example.card2card.entity.Transfer;
import com.example.card2card.entity.TransferStatus;
import com.example.card2card.repository.CardRepository;
import com.example.card2card.repository.TransferRepository;
import com.example.card2card.service.TransferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferServiceImpl implements TransferService {


    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransferRepository transferRepository;

    @Transactional
    @Override
    public TransferResponse transfer(TransferRequest request) {
        Card source = cardRepository.findByCardNumber(request.getSourceCardNumber())
                .orElseThrow(() -> new RuntimeException("کارت مبدا یافت نشد"));

        Card destination = cardRepository.findByCardNumber(request.getDestinationCardNumber())
                .orElseThrow(() -> new RuntimeException("کارت مقصد یافت نشد"));

        if (source.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("موجودی کافی نیست");
        }

        if (source.getTransferLimit().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("مبلغ از سقف مجاز بیشتر است");
        }

        if (source.getCardNumber().equals(destination.getCardNumber())) {
            throw new RuntimeException("نمی‌توان به همان کارت پول انتقال داد");
        }


        Transfer transfer = new Transfer();
        transfer.setSourceCard(source);
        transfer.setDestinationCard(destination);
        transfer.setAmount(request.getAmount());
        transfer.setCreatedAt(LocalDateTime.now());
        transfer.setStatus(TransferStatus.PENDING);

        transferRepository.save(transfer);

        BigDecimal amount = request.getAmount();
        source.setBalance(source.getBalance().subtract(amount));
        destination.setBalance(destination.getBalance().add(amount));

        cardRepository.save(source);
        cardRepository.save(destination);

        transfer.setStatus(TransferStatus.SUCCESS);
        transferRepository.save(transfer);

        return new TransferResponse(
                destination.getOwner().getUsername(),
                destination.getCardNumber(),
                request.getAmount()
        );
    }
}
