package br.com.packagingby.layer.account.controllers;

import br.com.packagingby.layer.account.DTOs.CreateTransactionRequest;
import br.com.packagingby.layer.account.services.CreateTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/transaction")
public class CreateTransactionController {

    @Autowired
    private CreateTransaction createTransaction;

    @PostMapping("/")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionRequest newTransactionRequest) {
        try {
            createTransaction.createTransaction(newTransactionRequest);
            return new ResponseEntity<>("Transaction created.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Fail to create transaction", HttpStatus.BAD_REQUEST);
        }

    }
}
