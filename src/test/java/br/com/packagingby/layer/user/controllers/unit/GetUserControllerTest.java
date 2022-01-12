package br.com.packagingby.layer.user.controllers.unit;

import br.com.packagingby.layer.user.controllers.GetUserController;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.services.GetUserService;
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

@ExtendWith(SpringExtension.class)
class GetUserControllerTest {

    @InjectMocks
    private GetUserController getUserController;

    @Mock
    private GetUserService getUserServiceMock;

    @BeforeEach
    void setUp() {
        List<User> userList = new ArrayList<>(List.of(UserData.createValidUser()));

        BDDMockito.when(getUserServiceMock.getAllUser())
                .thenReturn(userList);

        BDDMockito.when(getUserServiceMock.getUserById(ArgumentMatchers.longThat(
                argument -> argument == 1L
        )))
                .thenReturn(UserData.createValidUser());

        BDDMockito.when(getUserServiceMock.getUserByUsername(ArgumentMatchers.matches("bteodosio")))
                .thenReturn(UserData.createValidUser());
    }

    @Test
    @DisplayName("Should return a list of all users saved")
    void shouldReturnAListOfAllUsersSaved() {
        String validUserName = UserData.createValidUser().getName();
        List<User> userList = getUserController.getAllUsers().getBody();

        Assertions.assertThat(userList).isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(userList.get(0).getName()).isEqualTo(validUserName);
    }

    @Test
    @DisplayName("Should return user information when user id is provided")
    void shouldReturnUserInformationWhenUserIdIsProvided() {
        User validUser = UserData.createValidUser();

        User foundUser = this.getUserController.getUserById(validUser.getId()).getBody();

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

        User foundUser = this.getUserController.getUserByUsername(validUser.getUsername()).getBody();

        Assertions.assertThat(foundUser)
                .isNotNull();

        Assertions.assertThat(foundUser.getId())
                .isNotNull()
                .isEqualTo(validUser.getId());

    }

    @Test
    @DisplayName("Should not return user information when user id does not exist")
    void shouldNotReturnUserInformationWhenUserIdDoesNotExist() {

        User foundUser = this.getUserController.getUserById(0L).getBody();

        Assertions.assertThat(foundUser)
                .isNull();

    }

    @Test
    @DisplayName("Should not return user information when username does not exist")
    void shouldNotReturnUserInformationWhenUsernameDoesNotExist() {
        User foundUser = this.getUserController.getUserByUsername("NotExist").getBody();

        Assertions.assertThat(foundUser)
                .isNull();
    }

}