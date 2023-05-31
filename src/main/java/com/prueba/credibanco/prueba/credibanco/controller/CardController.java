package com.prueba.credibanco.prueba.credibanco.controller;


import com.prueba.credibanco.prueba.credibanco.dto.CardBalanceRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardResponse;
import com.prueba.credibanco.prueba.credibanco.service.impl.CardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("card")
public class CardController {

    @Autowired
    CardService cardService;

    /*
     Generate Card Number
     */
    @ApiOperation (value = "Generate Card Number", notes = "Se realiza la generacion de tarjeta")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @GetMapping (path = "{productId}/number")
    public ResponseEntity<CardResponse> generateCardNumber(@PathVariable String productId, @RequestBody CardRequest cardRequest) {
        CardResponse cardResponse = cardService.generateCardNumber(productId, cardRequest);
        return ResponseEntity.status(cardResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cardResponse);
    }
    @ApiOperation (value = "Activar tarjeta", notes = "Activacion de tarjerta")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @PostMapping (path = "activate-card", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponse> activateCard(@RequestBody CardRequest cardId) {
        CardResponse cardResponse = cardService.activateCard(cardId);
        return ResponseEntity.status(cardResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cardResponse);
    }
    @ApiOperation (value = "Bloquear Tarjeta", notes = "Bloqueo de tarjerta")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @DeleteMapping("{cardId}")
    public ResponseEntity<CardResponse> blockCard(@PathVariable String cardId) {
        CardResponse cardResponse = cardService.blockCard(cardId);
        return ResponseEntity.status(cardResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cardResponse);
    }
    @ApiOperation (value = "Recarga de tarjeta", notes = "Recarga de tarjerta")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @PostMapping("balance")
    public ResponseEntity<CardResponse> balanceCard(@RequestBody CardBalanceRequest cardBalanceRequest){
        CardResponse cardResponse = cardService.balanceCard(cardBalanceRequest);
        return ResponseEntity.status(cardResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cardResponse);
    }
    @ApiOperation (value = "Consulta de saldo", notes = "Consulta de saldo")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @GetMapping ("balance/{carId}")
    public ResponseEntity<CardResponse> consultBalance(@PathVariable Long carId) {
        CardResponse cardResponse = cardService.consultBalance(carId);
        return ResponseEntity.status(cardResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(cardResponse);
    }
}
