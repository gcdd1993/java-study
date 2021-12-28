package io.github.gcdd1993.service;

import io.github.gcdd1993.persistence.model.User;
import io.github.gcdd1993.web.dto.UserDto;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
public interface IUserService {
    User registerNewUserAccount(UserDto userDto);
}