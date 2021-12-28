package io.github.gcdd1993.persistence.dao;

import io.github.gcdd1993.persistence.model.NewLocationToken;
import io.github.gcdd1993.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken, Long> {

    NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UserLocation userLocation);

}
