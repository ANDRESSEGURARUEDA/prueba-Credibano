package com.prueba.credibanco.prueba.credibanco.repository;

import com.prueba.credibanco.prueba.credibanco.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {

    TransactionEntity findByTransactionId(String transactionId);
}
