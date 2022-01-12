package br.com.packagingby.layer.account.services;

import br.com.packagingby.layer.account.DTOs.AccountResponse;
import br.com.packagingby.layer.account.entities.Account;
import br.com.packagingby.layer.account.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAccounts {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AccountResponse getAccountByNumber (String accountNumber) throws Exception {
        Account accountFound = new Account();

        if(!accountFound.setAccountNumberByString(accountNumber)){
            throw new IllegalArgumentException("Missing Account argument.");
        }

        accountFound = accountRepository.findByAccountNumberAndDigit(accountFound.getAccountNumber(), accountFound.getDigit());

        if(accountFound == null){
            throw new Exception("Account not found.");
        }

        return modelMapper.map(accountFound, AccountResponse.class);
    }

    public List<AccountResponse> getAllAccounts () {

        return modelMapper.map(accountRepository.findAll(),new TypeToken<List<AccountResponse>>() {}.getType());
    }

}
