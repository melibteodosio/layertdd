package br.com.packagingby.layer.user.repositories;

import br.com.packagingby.layer.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
     User findByEmail(String email);
     Optional<User> findByUsername(String username);
}

