package br.com.packagingby.layer.user.controllers.unit;

import br.com.packagingby.layer.exceptions.BadRequestException;
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
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ValidationException;


@ExtendWith(SpringExtension.class)
class CreateUserControllerTest {
    @InjectMocks
    private CreateUserController createUserController;

    @Mock
    private CreateUserService createUserServiceMock;

    @BeforeEach
    void setUpMocks(){
        BDDMockito.when(createUserServiceMock.saveUser(ArgumentMatchers.isA(User.class)))
                .thenAnswer(invocation -> {
                    User userExists = invocation.getArgument(0, User.class);
                    if (userExists.getUsername().equals("alreadyExists")) {
                        throw new BadRequestException("User already exists");
                    } else if (userExists.getEmail().equals("alreadyExists@gmail.com")) {
                        throw new BadRequestException("User already exists");
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
                .isPositive();
    }

    @Test
    @DisplayName("Should not create a new user when required information is not provided")
    void shouldNotCreateANewUserWhenRequiredInformationIsNotProvided(){
        BDDMockito.when(createUserServiceMock.saveUser(ArgumentMatchers.isA(User.class)))
                .thenThrow(ValidationException.class);

        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setName("");

        Assertions.assertThatThrownBy(() -> createUserController.createUser(userToBeSaved))
                .isInstanceOf(ValidationException.class);

    }

    @Test
    @DisplayName("Should not create a new user when email is already exists")
    void shouldNotCreateANewUserWhenEmailIsAlreadyExists() {
        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setEmail("alreadyExists@gmail.com");

        Assertions.assertThatThrownBy(() -> createUserController.createUser(userToBeSaved))
                .hasMessage("User already exists")
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("Should not create a new user when username is already exists")
    void shouldNotCreateANewUserWhenUsernameIsAlreadyExists() {
        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setUsername("alreadyExists");

        Assertions.assertThatThrownBy(() -> createUserController.createUser(userToBeSaved))
                .hasMessage("User already exists")
                .isInstanceOf(BadRequestException.class);

    }

}