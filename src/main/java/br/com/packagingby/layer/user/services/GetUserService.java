package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.exceptions.BadRequestException;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        User foundUser = usersRepository.findByUsername(username);
        if (foundUser == null) {
            throw new BadRequestException("User not found");
        }
        return foundUser;
    }
}
