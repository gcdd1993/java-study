package io.github.gcdd1993.service;

import io.github.gcdd1993.persistence.dao.UserRepository;
import io.github.gcdd1993.persistence.dao.VerificationTokenRepository;
import io.github.gcdd1993.persistence.model.User;
import io.github.gcdd1993.persistence.model.VerificationToken;
import io.github.gcdd1993.web.dto.UserDto;
import io.github.gcdd1993.web.error.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

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

    @Override
    public User getUser(String verificationToken) {
        User user = verificationTokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return null;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
