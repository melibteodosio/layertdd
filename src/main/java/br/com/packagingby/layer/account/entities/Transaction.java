package br.com.packagingby.layer.account.entities;

import br.com.packagingby.layer.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private TransactionType type;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responsible;

}
