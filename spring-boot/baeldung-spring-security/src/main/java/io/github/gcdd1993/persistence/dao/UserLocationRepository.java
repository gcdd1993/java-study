package io.github.gcdd1993.persistence.dao;

import io.github.gcdd1993.persistence.model.User;
import io.github.gcdd1993.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);

}
