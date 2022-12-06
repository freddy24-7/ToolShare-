package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//Below annotation required for testing repository
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    @Disabled
    void findByUsername() {
    }

    @Test
    @Disabled
    void updateUserRole() {
    }
    @Test
    void itShouldSaveDataToDatabase() {
        //Assert
        User anotherUser = new User();
        anotherUser.setUsername("frank");
        anotherUser.setPassword("Avatar5001");
        //Act
        User newUser = underTest.save(anotherUser);
        //Assert
        assertNotNull(newUser);
        assertThat(newUser.getId()).isNotEqualTo(null);

    }
}