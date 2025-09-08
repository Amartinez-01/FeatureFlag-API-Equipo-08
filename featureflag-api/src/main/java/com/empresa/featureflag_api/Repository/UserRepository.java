package com.empresa.featureflag_api.Repository;

import com.empresa.featureflag_api.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,String> {
    Optional<User> findByemail(String email);
    boolean existsByusername (String username);
}

