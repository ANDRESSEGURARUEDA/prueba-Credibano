package com.prueba.credibanco.prueba.credibanco.service.impl;


import com.prueba.credibanco.prueba.credibanco.dto.CardBalanceRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardRequest;
import com.prueba.credibanco.prueba.credibanco.dto.CardResponse;
import com.prueba.credibanco.prueba.credibanco.entity.CardEntity;
import com.prueba.credibanco.prueba.credibanco.repository.ICardRepository;
import com.prueba.credibanco.prueba.credibanco.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class CardService implements ICardService {

    @Autowired
    ICardRepository cardRepository;

    private static final Logger logger = Logger.getLogger(CardService.class.getName());

    @Override
    public CardResponse generateCardNumber(String productId, CardRequest cardRequest) {
        CardResponse cardResponse = new CardResponse();
        try {
            int numericProductId = Integer.parseInt(productId);
            if (productId.length() == 6) {
                Random random = new Random();
                String numeroAleatorio = String.valueOf((long) (random.nextDouble() * 9000000000L) + 1000000000L);
                long cardId = Long.parseLong(numericProductId + numeroAleatorio);
                CardEntity cardEntity = new CardEntity();
                cardEntity.setCardId(cardId);
                cardEntity.setName(cardRequest.getName());
                cardEntity.setLastName(cardRequest.getLastName());
                cardEntity.setBalance(0);
                cardEntity.setStatusCard("Inactiva");
                cardEntity.setCreationDate(LocalDateTime.now());
                cardEntity.setExpirationDate(LocalDateTime.now().plusYears(3));
                cardEntity = cardRepository.save(cardEntity);
                if (cardEntity != null) {
                    cardResponse.setStatusCode("00");
                    cardResponse.setMessage("Tarjeta emitida con exito");
                    cardResponse.setCardId(cardId);
                    return cardResponse;
                } else {
                    cardResponse.setStatusCode("01");
                    cardResponse.setMessage("No fue posible emitir la tarjeta");
                }
            } else {
                cardResponse.setStatusCode("01");
                cardResponse.setMessage("El parámetro productId no puede ser menor ni mayor a 6 digitos");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El parámetro productId debe ser un número entero", e);
        }
        return cardResponse;
    }

    @Override
    public CardResponse activateCard(CardRequest card) {
        CardResponse cardResponse = new CardResponse();
        try {
            Long cardId = Long.parseLong(card.getCardId());
            CardEntity cardEntity = cardRepository.findByCardId(cardId);
            if (cardEntity != null) {
                cardEntity.setStatusCard("Activa");
                cardResponse.setStatusCode("00");
                cardResponse.setMessage("Su Tarjeta fue activada exitosamente");
                cardResponse.setCardId(cardId);
                cardRepository.save(cardEntity);
                return cardResponse;
            }
            cardResponse.setStatusCode("01");
            cardResponse.setMessage("El cardId no esxixte, o no puede ser menor ni mayor a 16 digitos");
        } catch (Exception e) {
            throw new IllegalArgumentException("El cardId no debe contener caracteres", e);
        }
        return cardResponse;
    }

    @Override
    public CardResponse blockCard(String cardId) {
        CardResponse cardResponse = new CardResponse();
        try {
            Long card = Long.parseLong(cardId);
            CardEntity cardEntity = cardRepository.findByCardId(card);
            if (cardEntity != null) {
                cardEntity.setStatusCard("Bloqueada");
                cardResponse.setStatusCode("00");
                cardResponse.setMessage("Su Tarjeta fue Bloqueada con exito");
                cardResponse.setCardId(card);
                cardRepository.save(cardEntity);
                return cardResponse;
            }
            cardResponse.setStatusCode("01");
            cardResponse.setMessage("El cardId que intentas bloquear no esxixte");
        } catch (Exception e) {
            throw new IllegalArgumentException("El cardId no debe contener caracteres", e);
        }
        return cardResponse;
    }

    @Override
    public CardResponse balanceCard(CardBalanceRequest cardBalanceRequest) {
        CardResponse cardResponse = new CardResponse();
        try {
            CardEntity cardEntity = cardRepository.findByCardId(cardBalanceRequest.getCardId());
            if (cardEntity != null) {
                if (cardEntity.getStatusCard().equals("Bloqueada")) {
                    cardResponse.setStatusCode("01");
                    cardResponse.setMessage("Su tarjeta se encuentra bloqueada");
                    cardResponse.setCardId(cardBalanceRequest.getCardId());
                    return cardResponse;
                }
                if (cardEntity.getStatusCard().equals("Inactiva")) {
                    cardResponse.setStatusCode("01");
                    cardResponse.setMessage("Su tarjeta se encuentra Inactiva");
                    cardResponse.setCardId(cardBalanceRequest.getCardId());
                    return cardResponse;
                }
                cardEntity.setBalance(cardBalanceRequest.getBalance());
                cardResponse.setStatusCode("00");
                cardResponse.setMessage("Su tarjeta fue Recargada con exito");
                cardResponse.setCardId(cardBalanceRequest.getCardId());
                cardRepository.save(cardEntity);
                return cardResponse;
            }
            cardResponse.setStatusCode("01");
            cardResponse.setMessage("Su tarjeta no esxixte");
            cardResponse.setCardId(cardBalanceRequest.getCardId());
        } catch (Exception e) {
            throw new IllegalArgumentException("El cardId no debe contener caracteres");
        }
        return cardResponse;
    }

    @Override
    public CardResponse consultBalance(Long cardId) {
        CardResponse cardResponse = new CardResponse();
        CardEntity cardEntity = cardRepository.findByCardId(cardId);
        if (cardEntity != null) {
            cardResponse.setStatusCode("00");
            cardResponse.setMessage("Tienes un saldo de: " + cardEntity.getBalance());
            cardResponse.setCardId(cardId);
            return cardResponse;
        }
        cardResponse.setStatusCode("01");
        cardResponse.setMessage("Tu tarjeta no exixte");
        cardResponse.setCardId(cardId);
        return cardResponse;
    }

}
