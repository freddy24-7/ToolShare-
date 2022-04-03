package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLastName(String lastName);

    Optional<User> findUserByEmail(String email);

    //Custom Query
    @Query("SELECT u FROM User u WHERE u.email= ?1")
    User findByEmail(String email);

    @Query("" +
            "SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM User u " +
            "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);

}
