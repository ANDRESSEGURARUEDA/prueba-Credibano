package com.prueba.credibanco.prueba.credibanco.test;

import com.prueba.credibanco.prueba.credibanco.controller.CardController;
import com.prueba.credibanco.prueba.credibanco.dto.CardBalanceRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardResponse;
import com.prueba.credibanco.prueba.credibanco.service.impl.CardService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CardControllerTest {
    @Autowired
    CardController cardController;
    @MockBean
    private CardService cardService;

    @Test
    public void generateCardNumber() {

        String productId = "123456";

        CardRequest cardRequest = new CardRequest();
        cardRequest.setName("Andres");
        cardRequest.setLastName("Segura");

        CardResponse cardResponse = new CardResponse();
        cardResponse.setStatusCode("00");
        cardResponse.setMessage("Exitoso");
        cardResponse.setCardId(1234567891234567L);

        Mockito.when(cardService.generateCardNumber(productId,cardRequest)).thenReturn(cardResponse);
        ResponseEntity<CardResponse> response = cardController.generateCardNumber(productId, cardRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(cardResponse, response.getBody());

    }
    @Test
    public void activateCard() {
        CardRequest cardRequest = new CardRequest();
        cardRequest.setCardId("1234567891234567");

        CardResponse cardResponse = new CardResponse();
        cardResponse.setStatusCode("00");
        cardResponse.setMessage("Exitoso");
        cardResponse.setCardId(1234567891234567L);

        Mockito.when(cardService.activateCard(Mockito.any(CardRequest.class))).thenReturn(cardResponse);
        ResponseEntity<CardResponse> response = cardController.activateCard(cardRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(cardResponse, response.getBody());
        Mockito.verify(cardService, Mockito.times(1)).activateCard(Mockito.any(CardRequest.class));

    }
    @Test
    public void blockCard(){
        String cardId = "1234567891234567";

        CardResponse cardResponse = new CardResponse();
        cardResponse.setStatusCode("00");
        cardResponse.setMessage("Su Tarjeta fue Bloqueada con exito");
        cardResponse.setCardId(1234567891234567L);

        Mockito.when(cardService.blockCard(cardId)).thenReturn(cardResponse);
        ResponseEntity<CardResponse> response = cardController.blockCard(cardId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(cardResponse, response.getBody());
    }

    @Test
    public void balanceCard(){
        CardBalanceRequest cardRequest = new CardBalanceRequest();

        CardResponse cardResponse = new CardResponse();
        cardResponse.setStatusCode("00");
        cardResponse.setMessage("Su Tarjeta fue recargada con exito");
        cardResponse.setCardId(1234567891234567L);

        Mockito.when(cardService.balanceCard(cardRequest)).thenReturn(cardResponse);
        ResponseEntity<CardResponse> response = cardController.balanceCard(cardRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(cardResponse, response.getBody());
    }

    @Test
    public void consultBalance(){
        Long cardId = 1234567891234567L;

        CardResponse cardResponse = new CardResponse();
        cardResponse.setStatusCode("00");
        cardResponse.setMessage("Su Tarjeta fue Bloqueada con exito");
        cardResponse.setCardId(1234567891234567L);

        Mockito.when(cardService.consultBalance(cardId)).thenReturn(cardResponse);
        ResponseEntity<CardResponse> response = cardController.consultBalance(cardId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(cardResponse, response.getBody());
    }
}
