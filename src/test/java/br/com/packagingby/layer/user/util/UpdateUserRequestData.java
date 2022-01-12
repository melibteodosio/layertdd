package br.com.packagingby.layer.user.util;

import br.com.packagingby.layer.user.DTOs.UpdateUserRequest;

public class UpdateUserRequestData {

    public static UpdateUserRequest createUpdateByIdUserRequest() {
        return UpdateUserRequest.builder()
                .id(1L)
                .name("Bruno Teodosio 2")
                .email("teo@gmail.com")
                .build();
    }

    public static UpdateUserRequest createUpdateByUsernameUserRequest() {
        return UpdateUserRequest.builder()
                .username("bteodosio")
                .name("Bruno Teodosio 2")
                .email("teo@gmail.com")
                .build();
    }
}
