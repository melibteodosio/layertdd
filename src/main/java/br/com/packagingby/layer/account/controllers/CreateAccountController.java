package br.com.packagingby.layer.account.controllers;

import br.com.packagingby.layer.account.DTOs.AccountResponse;
import br.com.packagingby.layer.account.DTOs.CreateAccountRequest;
import br.com.packagingby.layer.account.services.CreateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class CreateAccountController {

    @Autowired
    private CreateAccount createAccount;

    @PostMapping("/")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest newAccount) {
        try {
            return new ResponseEntity<>(createAccount.createAccount(newAccount), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccountResponse(), HttpStatus.BAD_REQUEST);
        }
    }
}
