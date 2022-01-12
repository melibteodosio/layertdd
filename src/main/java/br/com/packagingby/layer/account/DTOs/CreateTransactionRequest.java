package br.com.packagingby.layer.account.DTOs;

import br.com.packagingby.layer.account.entities.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    private TransactionType type;
    private Double amount;
    private int agencyId;
    private String accountNumber;
    private String username;
}
