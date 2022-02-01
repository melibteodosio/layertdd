package br.com.packagingby.layer.account.services;

import br.com.packagingby.layer.account.DTOs.CreateTransactionRequest;
import br.com.packagingby.layer.account.entities.Account;

import br.com.packagingby.layer.account.entities.Transaction;
import br.com.packagingby.layer.account.entities.TransactionType;
import br.com.packagingby.layer.account.repositories.AccountRepository;
import br.com.packagingby.layer.account.repositories.TransactionRepository;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTransaction {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createTransaction(CreateTransactionRequest newTransactionRequest) throws Exception{
        User foundUser = usersRepository.findByUsername(newTransactionRequest.getUsername()).orElse(null);

        if (foundUser == null) {
            throw new Exception("User not found.");
        }

        Account foundAccount = new Account();

        if (foundAccount.setAccountNumberByString(newTransactionRequest.getAccountNumber())) {

            foundAccount = accountRepository.findByAccountNumberAndDigit(foundAccount.getAccountNumber(), foundAccount.getDigit());
        } else {
            throw new Exception("Wrong account number.");
        }

        if (foundAccount == null) {
            throw new Exception("Account not found.");
        }

        Transaction newTransaction = modelMapper.map(newTransactionRequest, Transaction.class);

        newTransaction.setAccountId(foundAccount);
        newTransaction.setResponsible(foundUser);
        if (newTransactionRequest.getType() == TransactionType.DEPOSIT) {
            newTransaction.setType(TransactionType.DEPOSIT);
        } else if (newTransactionRequest.getType() == TransactionType.WITHDRAW) {
            newTransaction.setType(TransactionType.WITHDRAW);
        }else {
            throw new Exception("Transaction type not supported.");
        }


        transactionRepository.save(newTransaction);

    }
}
