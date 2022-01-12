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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class GetUserServiceTest {

    @InjectMocks
    private GetUserService getUser;

    @Mock
    private UsersRepository usersRepositoryMock;

    @BeforeEach
    void setUp() {
        List<User> userList = new ArrayList<>(List.of(UserData.createValidUser()));

        BDDMockito.when(usersRepositoryMock.findAll())
                .thenReturn(userList);

        BDDMockito.when(usersRepositoryMock.findById(ArgumentMatchers.longThat(
                argument -> argument == 1L
        )))
                .thenReturn(Optional.of(UserData.createValidUser()));

        BDDMockito.when(usersRepositoryMock.findByUsername(ArgumentMatchers.matches("bteodosio")))
                .thenReturn(UserData.createValidUser());
    }

    @Test
    @DisplayName("Should return a list of all users saved")
    void shouldReturnAListOfAllUsersSaved() {
        String validUserName = UserData.createValidUser().getName();
        List<User> userList = getUser.getAllUser();

        Assertions.assertThat(userList).isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(userList.get(0).getName()).isEqualTo(validUserName);
    }

    @Test
    @DisplayName("Should return user information when user id is provided")
    void shouldReturnUserInformationWhenUserIdIsProvided() {
        User validUser = UserData.createValidUser();

        User foundUser = getUser.getUserById(validUser.getId());

        Assertions.assertThat(foundUser)
                .isNotNull();

        Assertions.assertThat(foundUser.getId())
                .isNotNull()
                .isEqualTo(validUser.getId());

    }

    @Test
    @DisplayName("Should return user information when username is provided")
    void shouldReturnUserInformationWhenUsernameIsProvided() {
        User validUser = UserData.createValidUser();

        User foundUser = getUser.getUserByUsername(validUser.getUsername());

        Assertions.assertThat(foundUser)
                .isNotNull();

        Assertions.assertThat(foundUser.getId())
                .isNotNull()
                .isEqualTo(validUser.getId());

    }

    @Test
    @DisplayName("Should not return user information when user id does not exist")
    void shouldNotReturnUserInformationWhenUserIdDoesNotExist() {

        User foundUser = getUser.getUserById(0L);

        Assertions.assertThat(foundUser)
                .isNull();

    }

    @Test
    @DisplayName("Should not return user information when username does not exist")
    void shouldNotReturnUserInformationWhenUsernameDoesNotExist() {
        User foundUser = getUser.getUserByUsername("NotExist");

        Assertions.assertThat(foundUser)
                .isNull();
    }

}