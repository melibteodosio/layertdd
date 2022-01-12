package br.com.packagingby.layer.account.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private int agency;
    private String accountNumber;
    private int digit;

    private UserResponse mainOwner;
}
