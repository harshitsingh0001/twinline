package com.example.twinlineTask.repos;


import com.example.twinlineTask.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByMobileNumber(String mobileNumber);
}
