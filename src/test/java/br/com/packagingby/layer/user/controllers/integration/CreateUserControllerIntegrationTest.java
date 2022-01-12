package br.com.packagingby.layer.user.controllers.integration;

import br.com.packagingby.layer.user.DTOs.CreateUserRequest;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.util.CreateUserRequestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class CreateUserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Should create a new user when all required information is provided")
    void shouldCreateANewUserWhenAllRequiredInformationIsProvided() {
        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setEmail("new.user@gmail.com");
        userToBeSaved.setUsername("newUser");

        ResponseEntity<User> savedUser = testRestTemplate.exchange("/users/",
                HttpMethod.POST,
                new HttpEntity<>(userToBeSaved),
                User.class);

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
    void shouldNotCreateANewUserWhenRequiredInformationIsNotProvided() {

        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();
        userToBeSaved.setName("");

        ResponseEntity<User> savedUser = testRestTemplate.exchange("/users/",
                HttpMethod.POST,
                new HttpEntity<>(userToBeSaved),
                User.class);

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

        testRestTemplate.exchange("/users/",
                HttpMethod.POST,
                new HttpEntity<>(userToBeSaved),
                User.class);

        userToBeSaved.setUsername("anotherUser");

        ResponseEntity<User> alreadySavedUser = testRestTemplate.exchange("/users/",
                HttpMethod.POST,
                new HttpEntity<>(userToBeSaved),
                User.class);

        Assertions.assertThat(alreadySavedUser)
                .isNotNull();

        Assertions.assertThat(alreadySavedUser.getBody())
                .isNull();

        Assertions.assertThat(alreadySavedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not create a new user when username is already exists")
    void shouldNotCreateANewUserWhenUsernameIsAlreadyExists() {
        CreateUserRequest userToBeSaved = CreateUserRequestData.createUserRequest();

        testRestTemplate.exchange("/users/",
                HttpMethod.POST,
                new HttpEntity<>(userToBeSaved),
                User.class);

        userToBeSaved.setEmail("anotherEmail");

        ResponseEntity<User> alreadySavedUser = testRestTemplate.exchange("/users/",
                HttpMethod.POST,
                new HttpEntity<>(userToBeSaved),
                User.class);

        Assertions.assertThat(alreadySavedUser)
                .isNotNull();

        Assertions.assertThat(alreadySavedUser.getBody())
                .isNull();

        Assertions.assertThat(alreadySavedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

    }

}