package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserService {
    private final UsersRepository usersRepository;

    public User deleteUserById(long id) throws Exception{
        User userExists = usersRepository.findById(id).orElse(null);

        if (userExists == null) {
            throw new Exception("User doesn't exists.");
        }

        usersRepository.deleteById(id);

        return userExists;
    }
}