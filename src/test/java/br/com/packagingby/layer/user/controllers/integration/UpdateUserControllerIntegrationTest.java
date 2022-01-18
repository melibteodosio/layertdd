package br.com.packagingby.layer.user.controllers.integration;

import br.com.packagingby.layer.user.DTOs.UpdateUserRequest;
import br.com.packagingby.layer.user.util.UpdateUserRequestData;
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
class UpdateUserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Should update user name when name is provided and user id exists")
    void shouldUpdateUserNameWhenNameIsProvidedAndUserIdExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setEmail(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);


        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getName())
                .isEqualTo(updateUserRequest.getName());

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNotNull();
    }

    @Test
    @DisplayName("Should not update user name when name is blank and user id exists")
    void shouldNotUpdateUserNameWhenNameIsBlankAndUserIdExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setName(" ");
        updateUserRequest.setEmail(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);


        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should update user email when email is provided and user id exists")
    void shouldUpdateUserEmailWhenEmailIsProvidedAndUserIdExists() {

        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setName(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getEmail())
                .isEqualTo(updateUserRequest.getEmail());

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNotNull();
    }

    @Test
    @DisplayName("Should not update user email when email is blank and user id exists")
    void shouldNotUpdateUserEmailWhenEmailIsBlankAndUserIdExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setName(null);
        updateUserRequest.setEmail(" ");

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);


        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should update user name when name is provided and username exists")
    void shouldUpdateUserNameWhenNameIsProvidedAndUsernameExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setEmail(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getName())
                .isEqualTo(updateUserRequest.getName());

        Assertions.assertThat(updatedUser.getBody().getEmail())
                .isNotNull();

    }

    @Test
    @DisplayName("Should update user email when email is provided and username exists")
    void shouldUpdateUserEmailWhenEmailIsProvidedAndUsernameExists() {

        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setName(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getEmail())
                .isEqualTo(updateUserRequest.getEmail());

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNotNull();
    }

    @Test
    @DisplayName("Should not update user when email and name is not provided and user id exists")
    void shouldNotUpdateUserWhenEmailAndNameIsNotProvidedAndUserIdExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setEmail(null);
        updateUserRequest.setName(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not update user when email and name is not provided and username exists")
    void shouldNotUpdateUserWhenEmailAndNameIsNotProvidedAndUsernameExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setEmail(null);
        updateUserRequest.setName(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not update user when email and name is empty and user id exists")
    void shouldNotUpdateUserWhenEmailAndNameIsEmptyAndUserIdExists() {

        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setEmail("");
        updateUserRequest.setName("");

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not update user when email and name is empty and username exists")
    void shouldNotUpdateUserWhenEmailAndNameIsEmptyAndUsernameExists() {

        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setEmail("");
        updateUserRequest.setName("");

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not update user when user id does not exists")
    void shouldNotUpdateUserWhenUserIdDoesNotExists() {

        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setId(-1L);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not update user when username does not exists")
    void shouldNotUpdateUserWhenUsernameDoesNotExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setUsername("");

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should not update user when username and id is zero")
    void shouldNotUpdateUserWhenUsernameAndIdIsZero() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setId(0L);
        updateUserRequest.setUsername(null);

        ResponseEntity<UpdateUserRequest> updatedUser = testRestTemplate.exchange("/users/",
                HttpMethod.PUT,
                new HttpEntity<>(updateUserRequest),
                UpdateUserRequest.class);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody().getName())
                .isNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

}