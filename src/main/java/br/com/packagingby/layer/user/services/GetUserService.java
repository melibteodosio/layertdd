package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final UsersRepository usersRepository;

    public List<User> getAllUser() {
        return usersRepository.findAll();
    }

    public User getUserById(long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
