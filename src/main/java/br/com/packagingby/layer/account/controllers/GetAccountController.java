package br.com.packagingby.layer.account.controllers;

import br.com.packagingby.layer.account.DTOs.AccountResponse;
import br.com.packagingby.layer.account.services.GetAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class GetAccountController {

    @Autowired
    private GetAccounts getAccounts;

    @GetMapping("/{account}")
    public ResponseEntity<AccountResponse> getAccountByNumber(@PathVariable("account") String accountNumber){
        AccountResponse accountFound = null;
        try {
            accountFound = getAccounts.getAccountByNumber(accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(accountFound, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(accountFound, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountResponse>> getAccountByNumber(){
        List<AccountResponse> accountsFound = null;

        try {
            accountsFound = getAccounts.getAllAccounts();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(accountsFound, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(accountsFound, HttpStatus.OK);
    }

}
