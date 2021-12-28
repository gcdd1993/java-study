package io.github.gcdd1993.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
@Data
@Entity
@Table(name = "user_account")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Column(length = 60)
    private String password;

    @Transient
    private List<String> roles;

}
