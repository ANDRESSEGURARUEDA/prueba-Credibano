package com.prueba.credibanco.prueba.credibanco.service;


import com.prueba.credibanco.prueba.credibanco.dto.ConsultTransactionResponse;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionAnulationRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionResponse;

public interface ITransactionalService {

    TransactionResponse createPurchase(TransactionRequest transactionRequest);

    ConsultTransactionResponse consultTransaction(String identifierTransaction);

    TransactionResponse anulationTransaction(TransactionAnulationRequest anulationRequest);


}
