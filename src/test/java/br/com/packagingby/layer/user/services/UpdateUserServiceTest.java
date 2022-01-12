package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.beans.ObjectMapper;
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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class UpdateUserServiceTest {

    @InjectMocks
    private UpdateUserService updateUserService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUpMocks() {

        BDDMockito.when(usersRepository.findById(ArgumentMatchers.longThat(argument -> argument == 1L)))
                .thenReturn(Optional.of(UserData.createValidUser()));

        BDDMockito.when(usersRepository.findByUsername(ArgumentMatchers.matches("bteodosio")))
                .thenReturn(UserData.createValidUser());

        BDDMockito.when(usersRepository.save(ArgumentMatchers.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        BDDMockito.when(objectMapper.mapNonNullValues(ArgumentMatchers.any(User.class), ArgumentMatchers.any(User.class)))
                .thenAnswer(invocation -> new ObjectMapper().mapNonNullValues(invocation.getArgument(0), invocation.getArgument(1)));

    }

    @Test
    @DisplayName("Should update user name when name is provided and user id exists")
    void shouldUpdateUserNameWhenNameIsProvidedAndUserIdExists() {
        User updateUserData = UserData.createUpdateByIdUserRequest();
        updateUserData.setEmail(null);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getName())
                .isEqualTo(updateUserData.getName());

    }

    @Test
    @DisplayName("Should update user email when email is provided and user id exists")
    void shouldUpdateUserEmailWhenEmailIsProvidedAndUserIdExists() {

        User updateUserData = UserData.createUpdateByIdUserRequest();
        updateUserData.setName(null);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getEmail())
                .isEqualTo(updateUserData.getEmail());
    }

    @Test
    @DisplayName("Should update user name when name is provided and username exists")
    void shouldUpdateUserNameWhenNameIsProvidedAndUsernameExists() {
        User updateUserData = UserData.createUpdateByUsernameUserRequest();
        updateUserData.setEmail(null);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getName())
                .isEqualTo(updateUserData.getName());

    }

    @Test
    @DisplayName("Should update user email when email is provided and username exists")
    void shouldUpdateUserEmailWhenEmailIsProvidedAndUsernameExists() {

        User updateUserData = UserData.createUpdateByUsernameUserRequest();
        updateUserData.setName(null);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNotNull();

        Assertions.assertThat(updatedUser.getEmail())
                .isEqualTo(updateUserData.getEmail());
    }

    @Test
    @DisplayName("Should not update user when email and name is not provided and user id exists")
    void shouldNotUpdateUserWhenEmailAndNameIsNotProvidedAndUserIdExists() {
        User updateUserData = UserData.createUpdateByIdUserRequest();
        updateUserData.setEmail(null);
        updateUserData.setName(null);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNull();
    }

    @Test
    @DisplayName("Should not update user when email and name is not provided and username exists")
    void shouldNotUpdateUserWhenEmailAndNameIsNotProvidedAndUsernameExists() {
        User updateUserData = UserData.createUpdateByUsernameUserRequest();
        updateUserData.setEmail(null);
        updateUserData.setName(null);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNull();
    }

    @Test
    @DisplayName("Should not update user when email and name is empty and user id exists")
    void shouldNotUpdateUserWhenEmailAndNameIsEmptyAndUserIdExists() {

        User updateUserData = UserData.createUpdateByIdUserRequest();
        updateUserData.setEmail("");
        updateUserData.setName("");

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNull();
    }

    @Test
    @DisplayName("Should not update user when email and name is empty and username exists")
    void shouldNotUpdateUserWhenEmailAndNameIsEmptyAndUsernameExists() {

        User updateUserData = UserData.createUpdateByUsernameUserRequest();
        updateUserData.setEmail("");
        updateUserData.setName("");

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNull();
    }

    @Test
    @DisplayName("Should not update user when user id does not exists")
    void shouldNotUpdateUserWhenUserIdDoesNotExists() {

        User updateUserData = UserData.createUpdateByIdUserRequest();
        updateUserData.setId(-1L);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNull();
    }

    @Test
    @DisplayName("Should not update user when username does not exists")
    void shouldNotUpdateUserWhenUsernameDoesNotExists() {
        User updateUserData = UserData.createUpdateByUsernameUserRequest();
        updateUserData.setUsername("");

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNull();
    }

    @Test
    @DisplayName("Should not update user when username and id is zero")
    void shouldNotUpdateUserWhenUsernameAndIdIsZero() {
        User updateUserData = UserData.createUpdateByIdUserRequest();
        updateUserData.setId(0L);
        updateUserData.setUsername(null);

        User updatedUser = updateUserService.updateUser(updateUserData);

        Assertions.assertThat(updatedUser)
                .isNull();
    }

}