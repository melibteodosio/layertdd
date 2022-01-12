package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final UsersRepository usersRepository;

    public User saveUser(User newUserRequest) throws Exception {
        User userAlreadyExists = usersRepository.findByUsername(newUserRequest.getUsername());

        if (userAlreadyExists != null) {
            throw new Exception("User already exists.");
        }

        userAlreadyExists = usersRepository.findByEmail(newUserRequest.getEmail());

        if (userAlreadyExists != null) {
            throw new Exception("User already exists.");
        }

        return usersRepository.save(newUserRequest);
    }
}
