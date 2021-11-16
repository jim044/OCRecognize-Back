package com.ocrecognize.repository;

import com.ocrecognize.model.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@TestPropertySource(locations = { "classpath:application-test.properties" })
@DataJpaTest
@Sql(scripts = "classpath:sql/insert_user_role.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername(){
        UserEntity userEntity = userRepository.findByUsername("Jimmy");
        assertNotNull(userEntity);
        assertEquals("Jimmy", userEntity.getUsername());
    }
}
