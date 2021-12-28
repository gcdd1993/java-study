package io.github.gcdd1993.service;

import io.github.gcdd1993.persistence.dao.UserRepository;
import io.github.gcdd1993.persistence.model.User;
import io.github.gcdd1993.web.dto.UserDto;
import io.github.gcdd1993.web.error.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User registerNewUserAccount(UserDto userDto) {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRoles(Arrays.asList("ROLE_USER"));

        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
