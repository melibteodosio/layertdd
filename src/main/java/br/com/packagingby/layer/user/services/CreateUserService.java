package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.exceptions.BadRequestException;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateUserService {
    private final UsersRepository usersRepository;

    public User saveUser(@NotNull User newUserRequest) {
        User userAlreadyExists = usersRepository.findByUsername(newUserRequest.getUsername());

        if (userAlreadyExists != null) {
            throw new BadRequestException("User already exists.");
        }

        userAlreadyExists = usersRepository.findByEmail(newUserRequest.getEmail());

        if (userAlreadyExists != null) {
            throw new BadRequestException("User already exists.");
        }

        return usersRepository.save(newUserRequest);
    }
}
