package com.example.card2card.service;

import com.example.card2card.config.RabbitConfig;
import com.example.card2card.dto.transfer.TransferRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransferProducer {

    private final RabbitTemplate rabbitTemplate;

    public TransferProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTransferRequest(TransferRequest request) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.TRANSFER_EXCHANGE,
                RabbitConfig.TRANSFER_ROUTING_KEY,
                request
        );
    }
}
