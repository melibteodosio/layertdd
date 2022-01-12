package br.com.packagingby.layer.user.controllers.unit;

import br.com.packagingby.layer.user.DTOs.CreateUserRequest;
import br.com.packagingby.layer.user.controllers.CreateUserController;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.CreateUserService;
import br.com.packagingby.layer.user.util.CreateUserRequestData;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ValidationException;

@ExtendWith(SpringExtension.class)
class CreateUserControllerTest {
    @InjectMocks
    private CreateUserController createUserController;

    @Mock
    private CreateUserService createUserServiceMock;

    @BeforeEach
    void setUpMocks() throws Exception {
        BDDMockito.when(createUserServiceMock.saveUser(ArgumentMatchers.isA(User.class)))
                .thenAnswer(invocation -> {
                    User userExists = invocation.getArgument(0, User.class);
                    if (userExists.getUsername().equals("alreadyExists")) {
                        return new Exception("User already exists");
                    } else if (userExists.getEmail().equals("alreadyExists@gmail.com")) {
                        return new Exception("User already exists");
                    }
                    return UserData.createValidUser();
                });
    }

    @Test
    @DisplayName("Should create a new user when all required information is provided")
    void shouldCreateANewUserWhenAllRequiredInformationIsProvided() {
        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();

        ResponseEntity<User> savedUser = createUserController.createUser(userToBeSaved);

        Assertions.assertThat(savedUser)
                .isNotNull();

        Assertions.assertThat(savedUser.getBody())
                .isNotNull();

        Assertions.assertThat(savedUser.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(savedUser.getBody().getId())
                .isGreaterThan(0L);
    }

    @Test
    @DisplayName("Should not create a new user when required information is not provided")
    void shouldNotCreateANewUserWhenRequiredInformationIsNotProvided() throws Exception {

        BDDMockito.when(createUserServiceMock.saveUser(ArgumentMatchers.isA(User.class)))
                .thenThrow(ValidationException.class);

        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setName("");

        ResponseEntity<User> savedUser = createUserController.createUser(userToBeSaved);

        Assertions.assertThat(savedUser)
                .isNotNull();

        Assertions.assertThat(savedUser.getBody())
                .isNull();

        Assertions.assertThat(savedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    @DisplayName("Should not create a new user when email is already exists")
    void shouldNotCreateANewUserWhenEmailIsAlreadyExists() {
        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setEmail("alreadyExists@gmail.com");

        ResponseEntity<User> savedUser = createUserController.createUser(userToBeSaved);

        Assertions.assertThat(savedUser)
                .isNotNull();

        Assertions.assertThat(savedUser.getBody())
                .isNull();

        Assertions.assertThat(savedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not create a new user when username is already exists")
    void shouldNotCreateANewUserWhenUsernameIsAlreadyExists() {
        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setUsername("alreadyExists");

        ResponseEntity<User> savedUser = createUserController.createUser(userToBeSaved);

        Assertions.assertThat(savedUser)
                .isNotNull();

        Assertions.assertThat(savedUser.getBody())
                .isNull();

        Assertions.assertThat(savedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

    }

}