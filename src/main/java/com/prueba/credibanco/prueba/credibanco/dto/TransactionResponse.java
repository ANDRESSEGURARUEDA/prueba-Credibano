package com.prueba.credibanco.prueba.credibanco.dto;

public class TransactionResponse {

    private String statusCode;
    private String message;
    private String transactionId;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
