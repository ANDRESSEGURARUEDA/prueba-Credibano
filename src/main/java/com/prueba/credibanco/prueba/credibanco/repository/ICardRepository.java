package com.prueba.credibanco.prueba.credibanco.repository;

import com.prueba.credibanco.prueba.credibanco.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<CardEntity, Long> {

    CardEntity findByCardId(Long carId);
}
