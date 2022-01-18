package br.com.packagingby.layer.user.controllers;

import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class GetUserController {
    private final GetUserService getUserService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User foundUser;
        try {
            foundUser = getUserService.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User foundUser;
        try {
            foundUser = getUserService.getUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers;
        try {
            allUsers = getUserService.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
