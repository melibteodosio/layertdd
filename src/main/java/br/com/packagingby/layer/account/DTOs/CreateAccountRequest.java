package br.com.packagingby.layer.account.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {
    private long id;
    private int agency;
    private String accountNumber;
    private int digit;
    private long mainOwnerID;
}
