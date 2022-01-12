package br.com.packagingby.layer.account.repositories;

import br.com.packagingby.layer.account.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByAccountNumberAndDigit(String accountNumber, int digit);
}
