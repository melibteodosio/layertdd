package br.com.packagingby.layer.user.repositories;

import br.com.packagingby.layer.user.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("User Repository Tests")
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DisplayName("Should create a new user when all required information is provided")
    void shouldCreateANewUserWhenAllRequiredInformationIsProvided() {
        User newUser = createNewUser();
        User savedUser = this.usersRepository.save(newUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(newUser.getUsername());
    }

    @Test
    @DisplayName("Should not save an user when name is empty")
    void shouldNotSaveAnUserWhenNameIsEmpty() {
        User newUser = new User(0, "", "teodosio@gmail.com", "bteodosio", "teste123");

//        Assertions.assertThatThrownBy(() -> this.usersRepository.save(newUser))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.usersRepository.save(newUser))
                .withMessageContaining("Name cannot be empty");
    }

    private User createNewUser() {
        return new User(0,"Bruno Teodosio","teodosio.goncalves@gmail.com","bteodosio","br902707");
    }

}