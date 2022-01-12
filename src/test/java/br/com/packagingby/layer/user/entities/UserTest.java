package br.com.packagingby.layer.user.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@DisplayName("User Entity tests")
class UserTest {

    private static final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("Should not create user when name is empty")
    void shouldNotCreateUserWhenRequiredInformationIsNotProvided() {
        User newUser = new User(0, "", "teodosio@gmail.com", "bteodosio", "teste123");
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

}