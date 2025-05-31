package com.example.card2card.service;

import com.example.card2card.config.RabbitConfig;
import com.example.card2card.dto.transfer.TransferRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransferConsumer {

    private final TransferService transferService;

    public TransferConsumer(TransferService transferService) {
        this.transferService = transferService;
    }

    @RabbitListener(queues = RabbitConfig.TRANSFER_QUEUE)
    public void receiveTransferRequest(TransferRequest request) {
        transferService.transfer(request);
    }
}
