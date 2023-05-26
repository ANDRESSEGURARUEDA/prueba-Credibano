package com.prueba.credibanco.prueba.credibanco.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "Card")
public class CardEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Long id;

    @Column (name = "cardId")
    private Long cardId;
    @Column (name = "name")
    private String name;
    @Column (name = "lastName")
    private String lastName;
    @Column (name = "balance")
    private int balance;
    @Column (name = "statusCard")
    private String statusCard;

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

    public Long getId() {
        return id;
    }
    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long carId) {
        this.cardId = carId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatusCard() {
        return statusCard;
    }

    public void setStatusCard(String statusCard) {
        this.statusCard = statusCard;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }


}
