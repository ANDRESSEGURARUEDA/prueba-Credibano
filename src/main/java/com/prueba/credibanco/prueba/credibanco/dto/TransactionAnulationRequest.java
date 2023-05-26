package com.prueba.credibanco.prueba.credibanco.dto;

public class TransactionAnulationRequest {

    private Long cardId;
    private String transactionId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
