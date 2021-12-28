package io.github.gcdd1993.persistence.dao;

import io.github.gcdd1993.persistence.model.User;
import io.github.gcdd1993.persistence.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
