package com.prueba.credibanco.prueba.credibanco.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "Transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Long id;
    @Column (name = "price")
    private int price;
    @Column (name = "transactionId")
    private String transactionId;

    @Column (name = "status_transaction")
    private String statusTransaction;

    @ManyToOne (fetch = FetchType.LAZY)
    @JsonIgnoreProperties ({"hibernateLazyInitializer", "handel"})
    @JoinColumn (name = "cardId")
    private CardEntity cardId;
    private LocalDateTime creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(String statusTransaction) {
        this.statusTransaction = statusTransaction;
    }

    public CardEntity getCardId() {
        return cardId;
    }

    public void setCardId(CardEntity cardId) {
        this.cardId = cardId;
    }
}
