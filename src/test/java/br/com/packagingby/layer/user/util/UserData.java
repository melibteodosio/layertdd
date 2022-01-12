package br.com.packagingby.layer.user.util;

import br.com.packagingby.layer.user.DTOs.UpdateUserRequest;
import br.com.packagingby.layer.user.entities.User;

public class UserData {

    public static User createUserToBeSaved() {
        return User.builder()
                .name("Bruno Teodosio")
                .email("teodosio.goncalves@gmail.com")
                .username("bteodosio")
                .password("br902707")
                .build();
    }

    public static User createValidUser() {
        return User.builder()
                .id(1L)
                .name("Bruno Teodosio")
                .email("teodosio.goncalves@gmail.com")
                .username("bteodosio")
                .password("br902707")
                .build();
    }

    public static User createUpdateByIdUserRequest() {
        return User.builder()
                .id(1L)
                .name("Bruno Teodosio 2")
                .email("teo@gmail.com")
                .build();
    }

    public static User createUpdateByUsernameUserRequest() {
        return User.builder()
                .username("bteodosio")
                .name("Bruno Teodosio 2")
                .email("teo@gmail.com")
                .build();
    }

    public static User createUpdatedUser() {
        return User.builder()
                .id(1L)
                .name("Bruno Teodosio 2")
                .email("teo@gmail.com")
                .username("bteodosio")
                .password("br902707")
                .build();
    }
}
