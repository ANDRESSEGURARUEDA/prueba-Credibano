package com.prueba.credibanco.prueba.credibanco.service.impl;


import com.prueba.credibanco.prueba.credibanco.dto.ConsultTransactionResponse;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionAnulationRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionResponse;
import com.prueba.credibanco.prueba.credibanco.entity.CardEntity;
import com.prueba.credibanco.prueba.credibanco.entity.TransactionEntity;
import com.prueba.credibanco.prueba.credibanco.repository.ICardRepository;
import com.prueba.credibanco.prueba.credibanco.repository.ITransactionRepository;
import com.prueba.credibanco.prueba.credibanco.service.ITransactionalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService implements ITransactionalService {

    @Autowired
    ITransactionRepository transactionRepository;
    @Autowired
    ICardRepository cardRepository;
    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public TransactionResponse createPurchase(TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = new TransactionResponse();
        TransactionEntity transaction = new TransactionEntity();
        CardEntity card = cardRepository.findByCardId(transactionRequest.getCardId());
        if (card != null) {
            if (card.getStatusCard().equals("Inactiva") || card.getStatusCard().equals("Bloqueada")) {
                transactionResponse.setStatusCode("01");
                transactionResponse.setMessage("Tu tarjeta se encuentra Incativa o Bloqueada comunicate con tu Banco");
                return transactionResponse;
            }
            if (card.getBalance() <= 0 || card.getBalance() < transactionRequest.getPrice()) {
                transactionResponse.setStatusCode("01");
                transactionResponse.setMessage("No tienes saldo disponible para realizar compras");
                return transactionResponse;
            }
            if (card.getExpirationDate().isBefore(LocalDateTime.now())) {
                transactionResponse.setStatusCode("01");
                transactionResponse.setMessage("Tu tarjeta se encuentra vencida");
                return transactionResponse;
            }
            transaction.setPrice(transactionRequest.getPrice());
            transaction.setCreationDate(LocalDateTime.now());
            String transactionId = UUID.randomUUID().toString();
            transaction.setTransactionId(transactionId);
            transaction.setCardId(card);
            transaction.setStatusTransaction("Exitosa");
            transaction = transactionRepository.save(transaction);
            if (transaction != null) {
                int nuevoSaldo = card.getBalance() - transactionRequest.getPrice();
                card.setBalance(nuevoSaldo);
                cardRepository.save(card);
                transactionResponse.setStatusCode("00");
                transactionResponse.setMessage("Tu compra fue exitosa");
                transactionResponse.setTransactionId(transactionId);
                return transactionResponse;
            }
            transactionResponse.setStatusCode("01");
            transactionResponse.setMessage("Tu compra fue rechazada");
            return transactionResponse;
        }
        transactionResponse.setStatusCode("01");
        transactionResponse.setMessage("Su tarjeta no existe");
        return transactionResponse;
    }

    @Override
    public ConsultTransactionResponse consultTransaction(String transactionId) {
        ConsultTransactionResponse transactionResponse = new ConsultTransactionResponse();
        TransactionEntity transaction = transactionRepository.findByTransactionId(transactionId);
        if (transaction != null) {
            transactionResponse.setStatusCode("00");
            transactionResponse.setMessage("*************");
            modelMapper.map(transaction, transactionResponse);
            return transactionResponse;
        }
        transactionResponse.setStatusCode("01");
        transactionResponse.setMessage("Tu transaccion no existe");
        return transactionResponse;
    }

    @Override
    public TransactionResponse anulationTransaction(TransactionAnulationRequest anulationRequest) {
        TransactionResponse transactionResponse = new TransactionResponse();
        CardEntity card = cardRepository.findByCardId(anulationRequest.getCardId());
        TransactionEntity transaction = transactionRepository.findByTransactionId(anulationRequest.getTransactionId());
        try {
            if (transaction != null) {
                if (!transaction.getStatusTransaction().equals("Anulada")) {
                    if (card.getCardId().equals(transaction.getCardId().getCardId())) {
                        Duration duration = Duration.between(transaction.getCreationDate(), LocalDateTime.now());
                        Long hoursDifferences = duration.toHours();
                        System.out.println(transaction.getCardId().getId());
                        if (hoursDifferences < 24) {
                            transaction.setStatusTransaction("Anulada");
                            transactionRepository.save(transaction);
                            int nuevoSaldo = card.getBalance() + transaction.getPrice();
                            card.setBalance(nuevoSaldo);
                            cardRepository.save(card);
                            transactionResponse.setStatusCode("00");
                            transactionResponse.setMessage("El transaction fue anulada exitosamente");
                            transactionResponse.setTransactionId(anulationRequest.getTransactionId());
                            return transactionResponse;
                        } else {
                            transactionResponse.setStatusCode("01");
                            transactionResponse.setMessage("Superaste el tiempo limite para realizar la anulacion ");
                            transactionResponse.setTransactionId(anulationRequest.getTransactionId());
                            return transactionResponse;
                        }
                    }
                } else {
                    transactionResponse.setStatusCode("01");
                    transactionResponse.setMessage("Esta transaccion ya se encuentra anulada");
                    transactionResponse.setTransactionId(anulationRequest.getTransactionId());
                    return transactionResponse;
                }
            }
            transactionResponse.setStatusCode("01");
            transactionResponse.setMessage("El transactionId no existe");
            transactionResponse.setTransactionId(anulationRequest.getTransactionId());
        } catch (Exception e) {
            throw new IllegalArgumentException("El cardId no corresponde al transactionId");
        }
        return transactionResponse;
    }
}
