package com.example.card2card.controller;

import com.example.card2card.dto.transfer.TransferRequest;
import com.example.card2card.service.TransferProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle card-to-card transfer requests by sending them to the processing queue asynchronously.
 */
@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    private final TransferProducer transferProducer;

    @Autowired
    public TransferController(TransferProducer transferProducer) {
        this.transferProducer = transferProducer;
    }

    @PostMapping
    public ResponseEntity<String> doTransfer(@RequestBody TransferRequest request) {
        transferProducer.sendTransferRequest(request);
        return ResponseEntity.ok(" درخواست انتقال به صف ارسال شد و در حال پردازش است.");
    }

}
