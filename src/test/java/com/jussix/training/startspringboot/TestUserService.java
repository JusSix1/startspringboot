package com.jussix.training.startspringboot;

import com.jussix.training.startspringboot.entity.Address;
import com.jussix.training.startspringboot.entity.Social;
import com.jussix.training.startspringboot.entity.User;
import com.jussix.training.startspringboot.exception.UserException;
import com.jussix.training.startspringboot.service.AddressService;
import com.jussix.training.startspringboot.service.SocialService;
import com.jussix.training.startspringboot.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private SocialService socialService;

    @Autowired
    private AddressService addressService;

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
    void testCreateSocial() {

        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNull(social);

        social = socialService.create(
                user,
                SocialTestCreateData.facebook,
                SocialTestCreateData.line,
                SocialTestCreateData.instagram,
                SocialTestCreateData.tiktok
        );

        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
        Assertions.assertEquals(SocialTestCreateData.line, social.getLine());
        Assertions.assertEquals(SocialTestCreateData.instagram, social.getInstagram());
        Assertions.assertEquals(SocialTestCreateData.tiktok, social.getTiktok());

    }

    @Order(4)
    @Test
    void testCreateAddress() {

        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());

        createAddress(user, Address1TestCreateData.line1, Address1TestCreateData.line2, Address1TestCreateData.zipcode);
        createAddress(user, Address2TestCreateData.line1, Address2TestCreateData.line2, Address2TestCreateData.zipcode);

    }

    public void createAddress(User user, String line1, String line2, String zipcode){

        Address address = addressService.create(
                user,
                line1,
                line2,
                zipcode
        );

        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1, address.getLine1());
        Assertions.assertEquals(line2, address.getLine2());
        Assertions.assertEquals(zipcode, address.getZipcode());

    }

    @Order(5)
    @Test
    void testDelete(){
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        //Check social
        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
        Assertions.assertEquals(SocialTestCreateData.line, social.getLine());
        Assertions.assertEquals(SocialTestCreateData.instagram, social.getInstagram());
        Assertions.assertEquals(SocialTestCreateData.tiktok, social.getTiktok());

        //Check address
        List<Address> addresses = user.getAddresses();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(2, addresses.size());

        userService.deleteById(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestCreateData.email);

        Assertions.assertTrue(optDelete.isEmpty());
    }

    interface TestCreateData{
        String email = "bright@test.com";

        String password = "1234";

        String name = "Bright";
    }

    interface SocialTestCreateData{
        String facebook = "facebook name";

        String line = "Line ID";

        String instagram = "IG name";

        String tiktok = "Tiktok name";
    }

    interface Address1TestCreateData{
        String line1 = "line1 address1";

        String line2 = "line2 address1";

        String zipcode = "00001";
    }

    interface Address2TestCreateData{
        String line1 = "line1 address2";

        String line2 = "line2 address2";

        String zipcode = "00002";
    }


    interface TestUpdateData{
        String name = "BrightUpdate";
    }

}
