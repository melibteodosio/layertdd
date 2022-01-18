package br.com.packagingby.layer.user.controllers;

import br.com.packagingby.layer.user.DTOs.CreateUserRequest;
import br.com.packagingby.layer.user.DTOs.mapper.CreateUserRequestMapper;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class CreateUserController {
    private final CreateUserService createUserService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest newUserData) {
        User userToBeSaved = CreateUserRequestMapper.INSTANCE.createRequestToUser(newUserData);
        return new ResponseEntity<>(createUserService.saveUser(userToBeSaved), HttpStatus.CREATED);

    }
}
