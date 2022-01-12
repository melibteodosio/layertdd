package br.com.packagingby.layer.account.services;

import br.com.packagingby.layer.account.entities.Account;
import br.com.packagingby.layer.account.entities.TransactionType;
import br.com.packagingby.layer.account.repositories.AccountRepository;
import br.com.packagingby.layer.account.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTransaction {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Double getAccountBalance(String accountNumber) throws Exception{
        Account balanceAccount = new Account();
        balanceAccount.setAccountNumberByString(accountNumber);
        balanceAccount = accountRepository.findByAccountNumberAndDigit(balanceAccount.getAccountNumber(), balanceAccount.getDigit());

        if (balanceAccount == null) {
            throw new Exception("Account not found.");
        }

        Double depositTransactionSum = transactionRepository.getTransactionSum(TransactionType.DEPOSIT, balanceAccount.getId());
        Double withdrawTransactionSum = transactionRepository.getTransactionSum(TransactionType.WITHDRAW, balanceAccount.getId());

        return depositTransactionSum - withdrawTransactionSum;

    }
}
