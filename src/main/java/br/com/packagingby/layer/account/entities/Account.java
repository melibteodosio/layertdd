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
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int agency;
    private String accountNumber;
    private int digit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User mainOwner;

    public boolean setAccountNumberByString(String accountNumber) {
        String[] accountSpliced = accountNumber.split("-");
        if (accountSpliced.length < 2 || accountSpliced[0].isEmpty() || accountSpliced[1].isEmpty()) {
            return false;
        }
        this.accountNumber = accountSpliced[0];
        this.digit = Integer.parseInt(accountSpliced[1]);

        return true;
    }
}
