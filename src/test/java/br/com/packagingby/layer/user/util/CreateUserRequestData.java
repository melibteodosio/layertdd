package br.com.packagingby.layer.user.util;

import br.com.packagingby.layer.user.DTOs.CreateUserRequest;

public class CreateUserRequestData {

    public static CreateUserRequest createUserRequest() {
        return CreateUserRequest.builder()
                .name("Bruno Teste")
                .email("teste@gmail.com")
                .username("btest")
                .password("teste1234")
                .build();
    }
}
