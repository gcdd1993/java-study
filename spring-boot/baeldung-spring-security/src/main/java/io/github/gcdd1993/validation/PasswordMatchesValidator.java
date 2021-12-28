package io.github.gcdd1993.validation;

import io.github.gcdd1993.web.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserDto user = (UserDto) value;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
