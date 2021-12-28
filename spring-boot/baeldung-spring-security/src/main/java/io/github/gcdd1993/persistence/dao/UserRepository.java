package io.github.gcdd1993.persistence.dao;

import io.github.gcdd1993.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}