package br.com.packagingby.layer.user.controllers;

import br.com.packagingby.layer.user.DTOs.UpdateUserRequest;
import br.com.packagingby.layer.user.DTOs.mapper.UpdateUserRequestMapper;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UpdateUserController {

    private final UpdateUserService updateUserService;

    @PutMapping("/")
    public ResponseEntity<UpdateUserRequest> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        User userToUpdate = UpdateUserRequestMapper.INSTANCE.updateRequestToUser(updateUserRequest);
        User updatedUser = updateUserService.updateUser(userToUpdate);
        UpdateUserRequest updatedUserResponse = UpdateUserRequestMapper.INSTANCE.userToUpdateRequest(updatedUser);

        return new ResponseEntity<>(updatedUserResponse, HttpStatus.OK);

    }

}
