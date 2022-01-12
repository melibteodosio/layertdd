package br.com.packagingby.layer.user.repositories;

import br.com.packagingby.layer.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByUsername(String username);
}

