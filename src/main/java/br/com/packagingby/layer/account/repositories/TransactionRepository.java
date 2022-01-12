package br.com.packagingby.layer.account.repositories;

import br.com.packagingby.layer.account.entities.Transaction;
import br.com.packagingby.layer.account.entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(sum(tba.amount),0.0) FROM Transaction as tba WHERE tba.type = :type and tba.accountId.id = :accountId")
    public Double getTransactionSum(@Param("type") TransactionType type, @Param("accountId")long accountID);

}
