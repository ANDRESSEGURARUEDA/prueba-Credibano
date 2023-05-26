package com.prueba.credibanco.prueba.credibanco.test;


import com.prueba.credibanco.prueba.credibanco.controller.TransactionController;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionAnulationRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionConsultResponse;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionResponse;
import com.prueba.credibanco.prueba.credibanco.service.impl.TransactionService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

@SpringBootTest
public class TransactionControllerTest {

    @Autowired
    TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void createPurchase() {

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setCardId(1234567891234567L);
        transactionRequest.setPrice(500);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setStatusCode("00");
        transactionResponse.setMessage("Su compra fue exitosa");
        transactionResponse.setTransactionId("89d2aea2-b3bf-4e29-81c1-568a6665225d");

        Mockito.when(transactionService.createPurchase(transactionRequest)).thenReturn(transactionResponse);
        ResponseEntity<TransactionResponse> response = transactionController.createPurchase(transactionRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(transactionResponse, response.getBody());
    }

    @Test
    public void consultTransaction() {

        String transactionId = "89d2aea2-b3bf-4e29-81c1-568a6665225d";

        TransactionConsultResponse transactionResponse = new TransactionConsultResponse();
        transactionResponse.setStatusCode("00");
        transactionResponse.setMessage("***********");
        transactionResponse.setTransactionId("89d2aea2-b3bf-4e29-81c1-568a6665225d");
        transactionResponse.setPrice(500);
        transactionResponse.setStatusTransaction("Anulada");
        transactionResponse.setCreationDate(LocalDateTime.now());

        Mockito.when(transactionService.consultTransaction(transactionId)).thenReturn(transactionResponse);
        ResponseEntity<TransactionConsultResponse> response = transactionController.consultTransaction(transactionId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(transactionResponse, response.getBody());
    }

    @Test
    public void anulationTransaction() {

        TransactionAnulationRequest transactionRequest = new TransactionAnulationRequest();
        transactionRequest.setCardId(1234567891234567L);
        transactionRequest.setTransactionId("89d2aea2-b3bf-4e29-81c1-568a6665225d");

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setStatusCode("00");
        transactionResponse.setMessage("Su compra fue exitosa");
        transactionResponse.setTransactionId("89d2aea2-b3bf-4e29-81c1-568a6665225d");

        Mockito.when(transactionService.anulationTransaction(transactionRequest)).thenReturn(transactionResponse);
        ResponseEntity<TransactionResponse> response = transactionController.anulationTransaction(transactionRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(transactionResponse, response.getBody());
    }




}
