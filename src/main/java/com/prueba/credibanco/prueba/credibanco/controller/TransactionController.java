package com.prueba.credibanco.prueba.credibanco.controller;


import com.prueba.credibanco.prueba.credibanco.dto.TransactionConsultResponse;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionAnulationRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionRequest;
import com.prueba.credibanco.prueba.credibanco.dto.TransactionResponse;
import com.prueba.credibanco.prueba.credibanco.service.impl.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @ApiOperation (value = "Realizar Compra", notes = "Se realiza la creacion de compra")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @PostMapping (path = "purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> createPurchase(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse trasaction = transactionService.createPurchase(transactionRequest);
        return ResponseEntity.status(trasaction.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(trasaction);
    }
    @ApiOperation (value = "Consulta de transaccion", notes = "Se realiza la consulta de transaccion")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @GetMapping ("{transactionId}")
    public ResponseEntity<TransactionConsultResponse> consultTransaction(@PathVariable String transactionId) {
        TransactionConsultResponse consultTransactionResponse = transactionService.consultTransaction(transactionId);
        return ResponseEntity.status(consultTransactionResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(consultTransactionResponse);
    }
    @ApiOperation (value = "Anulacion de transaccion", notes = "Se realiza la anulaccion de transaccion")
    @ApiResponses ({@ApiResponse (code = 200, message = "OK"), @ApiResponse (code = 400, message = "NOT_FOUND")})
    @PostMapping("anulation")
    public ResponseEntity<TransactionResponse> anulationTransaction(@RequestBody TransactionAnulationRequest anulationRequest) {
        TransactionResponse transactionResponse = transactionService.anulationTransaction(anulationRequest);
        return ResponseEntity.status(transactionResponse.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(transactionResponse);
    }

}
