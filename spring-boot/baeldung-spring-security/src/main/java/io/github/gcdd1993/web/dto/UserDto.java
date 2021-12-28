package io.github.gcdd1993.web.dto;

import io.github.gcdd1993.validation.PasswordMatches;
import io.github.gcdd1993.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 3. The User DTO Object
 * <p>
 * We need a Data Transfer Object to send all of the registration information to our Spring backend.
 * The DTO object should have all the information we'll require later on when we create and populate our User object
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@PasswordMatches
@Data
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}
