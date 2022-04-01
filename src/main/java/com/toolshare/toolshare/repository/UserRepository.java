package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLastName(String lastName);

    Optional<User> findUserByEmail(String email);
}
