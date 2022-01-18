package br.com.packagingby.layer.user.controllers.integration;

import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.util.UserData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase

public class GetUserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Should return a list of all users saved")
    void shouldReturnAListOfAllUsersSaved() {
        long validUserId = UserData.createValidUser().getId();

        ResponseEntity<List<User>> getAllUserResponse = testRestTemplate.exchange("/users/list", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        Assertions.assertThat(getAllUserResponse.getBody()).isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(1);

        Assertions.assertThat(getAllUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(getAllUserResponse.getBody().get(0).getId()).isEqualTo(validUserId);
    }

    @Test
    @DisplayName("Should return user information when user id is provided")
    void shouldReturnUserInformationWhenUserIdIsProvided() {
        User validUser = UserData.createValidUser();

        ResponseEntity<User> foundUser = testRestTemplate.getForEntity("/users/{id}", User.class, validUser.getId());

        Assertions.assertThat(foundUser)
                .isNotNull();

        Assertions.assertThat(foundUser.getBody())
                .isNotNull();

        Assertions.assertThat(foundUser.getBody().getId())
                .isNotNull()
                .isEqualTo(validUser.getId());

        Assertions.assertThat(foundUser.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Should return user information when username is provided")
    void shouldReturnUserInformationWhenUsernameIsProvided() {
        User validUser = UserData.createValidUser();

        String urlRequest = "/users/username/".concat(validUser.getUsername());
        ResponseEntity<User> getUserByUsernameResponse = testRestTemplate.exchange(urlRequest,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        Assertions.assertThat(getUserByUsernameResponse.getBody())
                .isNotNull();

        Assertions.assertThat(getUserByUsernameResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(getUserByUsernameResponse.getBody().getId())
                .isNotNull()
                .isEqualTo(validUser.getId());

    }

    @Test
    @DisplayName("Should not return user information when user id does not exist")
    void shouldNotReturnUserInformationWhenUserIdDoesNotExist() {

        String urlRequest = "/users/0";
        ResponseEntity<User> getUserByIdExceptionResponse = testRestTemplate.exchange(urlRequest,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        Assertions.assertThat(getUserByIdExceptionResponse.getBody())
                .isNull();

        Assertions.assertThat(getUserByIdExceptionResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    @DisplayName("Should not return user information when username does not exist")
    void shouldNotReturnUserInformationWhenUsernameDoesNotExist() {
        String urlRequest = "/users/username/";
        ResponseEntity<User> getUserByUsernameExceptionResponse = testRestTemplate.exchange(urlRequest,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        Assertions.assertThat(getUserByUsernameExceptionResponse.getBody())
                .isNotNull();

        Assertions.assertThat(getUserByUsernameExceptionResponse.getBody().getUsername())
                .isNull();

        Assertions.assertThat(getUserByUsernameExceptionResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }



}
