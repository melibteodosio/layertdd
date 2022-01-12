package br.com.packagingby.layer.user.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
    private String username;
    private String password;
}
