package com.prueba.credibanco.prueba.credibanco.dto;

public class TransactionRequest {

    private Long cardId;
    private int price;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
