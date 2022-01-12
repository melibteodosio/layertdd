package br.com.packagingby.layer.account.services;

import br.com.packagingby.layer.account.DTOs.AccountResponse;
import br.com.packagingby.layer.account.DTOs.CreateAccountRequest;
import br.com.packagingby.layer.account.entities.Account;
import br.com.packagingby.layer.account.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CreateAccount {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AccountResponse createAccount(CreateAccountRequest newAccount) throws Exception{
        Account accountAlreadyExist = accountRepository.findByAccountNumberAndDigit(newAccount.getAccountNumber(), newAccount.getDigit());

        if (accountAlreadyExist != null) {
            throw new Exception("Account already exists.");
        }

        try {
            Account savedAccount = accountRepository.save(modelMapper.map(newAccount, Account.class));
            return modelMapper.map(savedAccount, AccountResponse.class);
        } catch (DataIntegrityViolationException e) {
            throw new Exception("User doesn't exists.");
        }
    }
}
