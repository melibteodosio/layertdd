package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.exceptions.BadRequestException;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetUserService {
    private final UsersRepository usersRepository;

    public List<User> getAllUser() {
        return usersRepository.findAll();
    }

    public User getUserById(long id) {
        return usersRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
    }

    public User getUserByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("User not found"));
    }

    public List<User> findAllByName(String name) {
        return usersRepository.findAllByName(name).orElseThrow(() -> new BadRequestException("Users not found"));
    }

}
