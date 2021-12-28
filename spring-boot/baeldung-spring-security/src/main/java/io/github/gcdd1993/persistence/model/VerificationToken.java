package io.github.gcdd1993.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * The VerificationToken entity must meet the following criteria:
 * <p>
 * It must link back to the User (via a unidirectional relation)
 * It will be created right after registration
 * It will expire within 24 hours following its creation
 * Has a unique, randomly generated value
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Data
@Entity
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(60);
    }
}
