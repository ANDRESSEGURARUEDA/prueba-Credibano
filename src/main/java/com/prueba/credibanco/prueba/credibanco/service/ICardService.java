package com.prueba.credibanco.prueba.credibanco.service;


import com.prueba.credibanco.prueba.credibanco.dto.CardBalanceRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardResponse;

public interface ICardService {

    CardResponse generateCardNumber(String productId, CardRequest cardRequest);
    CardResponse activateCard(CardRequest cardId);
    CardResponse blockCard(String cardId);
    CardResponse balanceCard(CardBalanceRequest cardBalanceRequest);
    CardResponse consultBalance(Long cardId);

}
