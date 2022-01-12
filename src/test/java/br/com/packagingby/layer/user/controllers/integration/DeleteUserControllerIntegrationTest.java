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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class DeleteUserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Should delete an existing user when id is provided")
    void shouldDeleteAnExistingUserWhenIdIsProvided() {
        User userToDelete = UserData.createValidUser();

        ResponseEntity<User> deletedUser = testRestTemplate.exchange("/users/{id}",
                HttpMethod.DELETE,
                null,
                User.class,
                userToDelete.getId());

        Assertions.assertThat(deletedUser)
                .isNotNull();

        Assertions.assertThat(deletedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(deletedUser.getBody())
                .isNotNull();

        Assertions.assertThat(deletedUser.getBody().getId())
                .isEqualTo(userToDelete.getId());


    }

    @Test
    @DisplayName("Should not delete an user when user not found")
    void shouldNotDeleteAnUserWhenUserNotFound() {

        ResponseEntity<User> userNotDeleted = testRestTemplate.exchange("/users/{id}",
                HttpMethod.DELETE,
                null,
                User.class,
                0L);

        Assertions.assertThat(userNotDeleted)
                .isNotNull();

        Assertions.assertThat(userNotDeleted.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(userNotDeleted.getBody())
                .isNull();

    }

}