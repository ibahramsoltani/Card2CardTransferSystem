package com.example.card2card.service;

import com.example.card2card.dto.transfer.TransferRequest;
import com.example.card2card.dto.transfer.TransferResponse;

public interface TransferService {
    TransferResponse transfer(TransferRequest request);

}
