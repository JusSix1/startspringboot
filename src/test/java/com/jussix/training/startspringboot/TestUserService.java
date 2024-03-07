package com.jussix.training.startspringboot;

import com.jussix.training.startspringboot.entity.User;
import com.jussix.training.startspringboot.exception.UserException;
import com.jussix.training.startspringboot.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void testCreate() throws UserException {
        User user = userService.create(TestCreateData.email, TestCreateData.password, TestCreateData.name);

        //Check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        //Check equals
        Assertions.assertEquals(TestCreateData.email, user.getEmail());
        Assertions.assertTrue(userService.matchPassword(TestCreateData.password, user.getPassword()));
        Assertions.assertEquals(TestCreateData.name, user.getName());
    }

    @Order(2)
    @Test
    void testUpdate() throws UserException {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
    }

    @Order(3)
    @Test
    void testDelete(){
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        userService.deleteById(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestCreateData.email);

        Assertions.assertTrue(optDelete.isEmpty());
    }

    interface TestCreateData{
        String email = "bright@test.com";

        String password = "1234";

        String name = "Bright";
    }

    interface TestUpdateData{
        String name = "BrightUpdate";
    }

}
