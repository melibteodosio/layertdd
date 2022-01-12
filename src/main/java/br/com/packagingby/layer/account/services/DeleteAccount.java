package br.com.packagingby.layer.account.services;

import br.com.packagingby.layer.account.DTOs.AccountResponse;
import br.com.packagingby.layer.account.entities.Account;
import br.com.packagingby.layer.account.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteAccount {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AccountResponse deleteAccountByNumber(String accountNumber) throws Exception{
        Account accountFound;

        String[] accountSpliced = accountNumber.split("-");
        if (accountSpliced.length < 2 || accountSpliced[0].isEmpty() || accountSpliced[1].isEmpty()){
            throw new IllegalArgumentException("Missing Account argument.");
        }
        accountFound = accountRepository.findByAccountNumberAndDigit(accountSpliced[0], Integer.parseInt(accountSpliced[1]));

        if (accountFound == null) {
            throw new Exception("Account doesn't exists.");
        }

        accountRepository.deleteById(accountFound.getId());

        return modelMapper.map(accountFound,AccountResponse.class);
    }
}
