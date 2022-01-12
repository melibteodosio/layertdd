package br.com.packagingby.layer.account.controllers;

import br.com.packagingby.layer.account.DTOs.AccountResponse;
import br.com.packagingby.layer.account.services.DeleteAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class DeleteAccountController {

    @Autowired
    private DeleteAccount deleteAccount;

    @DeleteMapping("/{account}")
    public ResponseEntity<AccountResponse> deleteAccountByNumber(@PathVariable("account") String accountNumber) {
        try {
            return new ResponseEntity<>(deleteAccount.deleteAccountByNumber(accountNumber), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccountResponse(), HttpStatus.BAD_REQUEST);
        }
    }
}
