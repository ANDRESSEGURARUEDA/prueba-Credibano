package com.prueba.credibanco.prueba.credibanco.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties (ignoreUnknown = true)
public class CardBalanceRequest {
    @JsonProperty ("cardId")
    private Long cardId;
    @JsonProperty ("balance")
    private int balance;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public int getBalance() {
        return balance;
    }

}
