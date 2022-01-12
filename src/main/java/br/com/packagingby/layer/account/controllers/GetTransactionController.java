package br.com.packagingby.layer.account.controllers;

import br.com.packagingby.layer.account.services.GetTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class GetTransactionController {

    @Autowired
    private GetTransaction getTransaction;

    @GetMapping("/balance/{account}")
    public ResponseEntity<Double> getAccountBalance(@PathVariable("account") String accountNumber) {
        try{
            return new ResponseEntity<>(getTransaction.getAccountBalance(accountNumber), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        }
    }
}
