package br.com.packagingby.layer.user.controllers.unit;

import br.com.packagingby.layer.user.controllers.DeleteUserController;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.DeleteUserService;
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

@ExtendWith(SpringExtension.class)
class DeleteUserControllerTest {

    @InjectMocks
    private DeleteUserController deleteUserController;

    @Mock
    private DeleteUserService deleteUserServiceMock;

    @BeforeEach
    void setUpMocks() throws Exception {
        BDDMockito.when(deleteUserServiceMock.deleteUserById(ArgumentMatchers.longThat(argument -> argument == 1L)))
                .thenReturn(UserData.createValidUser());

        BDDMockito.when(deleteUserServiceMock.deleteUserById(ArgumentMatchers.longThat(argument -> argument == 0L)))
                .thenThrow(Exception.class);
    }

    @Test
    @DisplayName("Should delete an existing user when id is provided")
    void shouldDeleteAnExistingUserWhenIdIsProvided() {
        User userToDelete = UserData.createValidUser();

        ResponseEntity<User> deletedUser = deleteUserController.deleteUser(userToDelete.getId());

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

        ResponseEntity<User> userNotDeleted = deleteUserController.deleteUser(0L);

        Assertions.assertThat(userNotDeleted)
                .isNotNull();

        Assertions.assertThat(userNotDeleted.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(userNotDeleted.getBody())
                .isNull();

    }

}