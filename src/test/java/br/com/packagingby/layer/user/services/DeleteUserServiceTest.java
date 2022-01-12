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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class DeleteUserServiceTest {

    @InjectMocks
    private DeleteUserService deleteUserService;

    @Mock
    private UsersRepository usersRepository;

    @BeforeEach
    void setUpMocks() {
        BDDMockito.when(usersRepository.findById(ArgumentMatchers.longThat(argument -> argument == 1L)))
                .thenReturn(Optional.of(UserData.createValidUser()));

        BDDMockito.doNothing().when(usersRepository).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Should delete an existing user when id is provided")
    void shouldDeleteAnExistingUserWhenIdIsProvided() {
        User userToDelete = UserData.createValidUser();

        Assertions.assertThatCode(() -> deleteUserService.deleteUserById(userToDelete.getId()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Should return user deleted when successful")
    void shouldReturnUserDeletedWhenSuccessful() throws Exception {
        User userToDelete = UserData.createValidUser();

        User deletedUser = deleteUserService.deleteUserById(userToDelete.getId());

        Assertions.assertThat(deletedUser.getId())
                .isEqualTo(userToDelete.getId());

    }

    @Test
    @DisplayName("Should not delete an user when user not found")
    void shouldNotDeleteAnUserWhenUserNotFound() {

        Assertions.assertThatThrownBy(() -> deleteUserService.deleteUserById(0L))
                .isInstanceOf(Exception.class)
                .hasMessage("User doesn't exists.");
    }

}