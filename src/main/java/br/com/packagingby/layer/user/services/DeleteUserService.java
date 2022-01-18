package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.exceptions.BadRequestException;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteUserService {
    private final UsersRepository usersRepository;

    public User deleteUserById(long id){
        User userExists = usersRepository.findById(id).orElse(null);

        if (userExists == null) {
            throw new BadRequestException("User doesn't exists.");
        }

        usersRepository.deleteById(id);

        return userExists;
    }
}