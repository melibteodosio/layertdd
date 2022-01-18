package br.com.packagingby.layer.user.controllers.unit;

import br.com.packagingby.layer.exceptions.BadRequestException;
import br.com.packagingby.layer.user.DTOs.UpdateUserRequest;
import br.com.packagingby.layer.user.controllers.UpdateUserController;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.UpdateUserService;
import br.com.packagingby.layer.user.util.UpdateUserRequestData;
import br.com.packagingby.layer.user.util.UserData;
import org.apache.commons.lang3.StringUtils;
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
class UpdateUserControllerTest {

    @InjectMocks
    private UpdateUserController updateUserController;

    @Mock
    private UpdateUserService updateUserService;

    @BeforeEach
    void setUpMocks() {
        BDDMockito.when(updateUserService.updateUser(ArgumentMatchers.isA(User.class)))
                .thenAnswer(invocation -> {
                    User userToUpdate = invocation.getArgument(0, User.class);
                    if (userToUpdate.getId() == 0L && StringUtils.isEmpty(userToUpdate.getUsername())) {
                        throw new BadRequestException("User not found");
                    } else {
                        return UserData.createUpdatedUser();
                    }
                });
    }

    @Test
    @DisplayName("Should update user name when name is provided and user id exists")
    void shouldUpdateUserNameWhenNameIsProvidedAndUserIdExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setEmail(null);

        ResponseEntity<UpdateUserRequest> updatedUser = updateUserController.updateUser(updateUserRequest);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getName())
                .isEqualTo(updateUserRequest.getName());

    }

    @Test
    @DisplayName("Should update user email when email is provided and user id exists")
    void shouldUpdateUserEmailWhenEmailIsProvidedAndUserIdExists() {

        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setName(null);

        ResponseEntity<UpdateUserRequest> updatedUser = updateUserController.updateUser(updateUserRequest);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getEmail())
                .isEqualTo(updateUserRequest.getEmail());
    }

    @Test
    @DisplayName("Should update user name when name is provided and username exists")
    void shouldUpdateUserNameWhenNameIsProvidedAndUsernameExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setEmail(null);

        ResponseEntity<UpdateUserRequest> updatedUser = updateUserController.updateUser(updateUserRequest);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getName())
                .isEqualTo(updateUserRequest.getName());

    }

    @Test
    @DisplayName("Should update user email when email is provided and username exists")
    void shouldUpdateUserEmailWhenEmailIsProvidedAndUsernameExists() {

        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setName(null);

        ResponseEntity<UpdateUserRequest> updatedUser = updateUserController.updateUser(updateUserRequest);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getBody())
                .isNotNull();

        Assertions.assertThat(updatedUser.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(updatedUser.getBody().getEmail())
                .isEqualTo(updateUserRequest.getEmail());
    }

    @Test
    @DisplayName("Should not update user when username does not exists")
    void shouldNotUpdateUserWhenUsernameDoesNotExists() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByUsernameUserRequest();
        updateUserRequest.setUsername("");

        Assertions.assertThatThrownBy(() -> updateUserController.updateUser(updateUserRequest))
                .hasMessage("User not found")
                .isInstanceOf(BadRequestException.class);

    }

    @Test
    @DisplayName("Should not update user when username and id is zero")
    void shouldNotUpdateUserWhenUsernameAndIdIsZero() {
        UpdateUserRequest updateUserRequest = UpdateUserRequestData.createUpdateByIdUserRequest();
        updateUserRequest.setId(0L);
        updateUserRequest.setUsername(null);

        Assertions.assertThatThrownBy(() -> updateUserController.updateUser(updateUserRequest))
                .hasMessage("User not found")
                .isInstanceOf(BadRequestException.class);
    }

}