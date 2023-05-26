package com.prueba.credibanco.prueba.credibanco.dto;

public class CardBalanceRequest {

    private Long cardId;
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

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
