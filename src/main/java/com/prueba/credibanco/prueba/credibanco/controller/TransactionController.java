package com.prueba.credibanco.prueba.credibanco.controller;


import com.prueba.credibanco.prueba.credibanco.dto.TransactionConsultResponse;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionAnulationRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionResponse;
import com.prueba.credibanco.prueba.credibanco.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping (path = "purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> createPurchase(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse trasaction = transactionService.createPurchase(transactionRequest);
        return ResponseEntity.status(trasaction.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(trasaction);
    }

    @GetMapping ("{transactionId}")
    public ResponseEntity<TransactionConsultResponse> consultTransaction(@PathVariable String transactionId) {
        TransactionConsultResponse consultTransactionResponse = transactionService.consultTransaction(transactionId);
        return ResponseEntity.status(consultTransactionResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(consultTransactionResponse);
    }

    @PostMapping("anulation")
    public ResponseEntity<TransactionResponse> anulationTransaction(@RequestBody TransactionAnulationRequest anulationRequest) {
        TransactionResponse transactionResponse = transactionService.anulationTransaction(anulationRequest);
        return ResponseEntity.status(transactionResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(transactionResponse);
    }

}
