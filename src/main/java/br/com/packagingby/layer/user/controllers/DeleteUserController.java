package br.com.packagingby.layer.user.controllers;

import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.DeleteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class DeleteUserController {
    private final DeleteUserService deleteUserService;

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        return new ResponseEntity<>(deleteUserService.deleteUserById(id), HttpStatus.OK);
    }
}