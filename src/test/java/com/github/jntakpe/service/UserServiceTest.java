package com.github.jntakpe.service;

import com.github.jntakpe.config.ActuatorUiConfig;
import com.github.jntakpe.domain.User;
import com.github.jntakpe.util.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Test des services associés à l'entité {@link com.github.jntakpe.domain.User}
 *
 * @author jntakpe
 */
@SpringApplicationConfiguration(classes = ActuatorUiConfig.class)
@ActiveProfiles(value = Profile.Constant.DEVELOPPEMENT)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeClass
    public void setUp() {
        mongoTemplate.dropCollection(User.class);
        User titi = new User("titi", "titi@mail.com", "password");
        User toto = new User("Toto", "toto@mail.com", "password");
        mongoTemplate.save(titi);
        mongoTemplate.save(toto);
    }

    @Test(expectedExceptions = UsernameNotFoundException.class)
    public void loadUserByUsernameTest_ShouldThrowUserNotFoundException() {
        userService.loadUserByUsername("notuser");
    }

    @Test
    public void loadUserByUsernameTest_ShouldAuthenticate() {
        String titi = "TITI";
        userService.loadUserByUsername(titi);
        List<User> titis = mongoTemplate.find(query(where("login").is("titi")), User.class);
        assertThat(titis).hasSize(1);
        assertThat(titis.get(0).getLastConnection()).isCloseTo(new Date(), 10000L);
    }

    @Test
    public void createUserTest_ShouldCreate() {
        String username = "newUser";
        Query query = query(where("login").is(username));
        assertThat(mongoTemplate.find(query, User.class)).isEmpty();
        User newUser = new User(username, "newuser@mail.com", "password");
        userService.create(newUser);
        List<User> actual = mongoTemplate.find(query, User.class);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0)).isEqualTo(newUser);
    }
}
