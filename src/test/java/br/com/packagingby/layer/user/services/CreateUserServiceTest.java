package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import br.com.packagingby.layer.user.util.UserData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;


@ExtendWith(SpringExtension.class)
class CreateUserServiceTest {

    @InjectMocks
    private CreateUserService createUserService;

    @Mock
    private UsersRepository usersRepository;

    @BeforeEach
    void setUpMocks() {
        BDDMockito.when(usersRepository.save(ArgumentMatchers.isA(User.class)))
                .thenReturn(UserData.createValidUser());

        BDDMockito.when(usersRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenAnswer(invocation -> {
                    String usernameToBeFound = invocation.getArgument(0, String.class);
                    if (usernameToBeFound.equals("alreadyExists")) {
                        return UserData.createValidUser();
                    }
                    return null;
                });

        BDDMockito.when(usersRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenAnswer(invocation -> {
                    String usernameToBeFound = invocation.getArgument(0, String.class);
                    if (usernameToBeFound.equals("alreadyExists@gmail.com")) {
                        return UserData.createValidUser();
                    }
                    return null;
                });
    }

    @Test
    @DisplayName("Should create a new user when all required information is provided")
    void shouldCreateANewUserWhenAllRequiredInformationIsProvided() throws Exception {
        User userToBeSaved = UserData.createUserToBeSaved();

        User savedUser = createUserService.saveUser(userToBeSaved);

        Assertions.assertThat(savedUser)
                .isNotNull();

        Assertions.assertThat(savedUser.getId())
                .isGreaterThan(0L);
    }

    @Test
    @DisplayName("Should not create a new user when required information is not provided")
    void shouldNotCreateANewUserWhenRequiredInformationIsNotProvided() {

        BDDMockito.when(usersRepository.save(ArgumentMatchers.isA(User.class)))
                .thenThrow(ConstraintViolationException.class);

        User userToBeSaved = UserData.createUserToBeSaved();
        userToBeSaved.setName("");

        Assertions.assertThatThrownBy(() -> createUserService.saveUser(userToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Should not create a new user when email is already exists")
    void shouldNotCreateANewUserWhenEmailIsAlreadyExists() {
        User userToBeSaved = UserData.createUserToBeSaved();
        userToBeSaved.setEmail("alreadyExists@gmail.com");

        Assertions.assertThatThrownBy(() -> createUserService.saveUser(userToBeSaved))
                .isInstanceOf(Exception.class);

    }

    @Test
    @DisplayName("Should not create a new user when username is already exists")
    void shouldNotCreateANewUserWhenUsernameIsAlreadyExists() {
        User userToBeSaved = UserData.createUserToBeSaved();
        userToBeSaved.setUsername("alreadyExists");

        Assertions.assertThatThrownBy(() -> createUserService.saveUser(userToBeSaved))
                .isInstanceOf(Exception.class);

    }

}