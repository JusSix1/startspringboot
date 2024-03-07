package com.jussix.training.startspringboot.business;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.jussix.training.startspringboot.entity.User;
import com.jussix.training.startspringboot.exception.FileException;
import com.jussix.training.startspringboot.mapper.UserMapper;
import com.jussix.training.startspringboot.model.MLoginRequest;
import com.jussix.training.startspringboot.model.MRegisterResponse;
import com.jussix.training.startspringboot.service.UserService;
import org.springframework.stereotype.Service;

import com.jussix.training.startspringboot.exception.BaseException;
import com.jussix.training.startspringboot.exception.UserException;
import com.jussix.training.startspringboot.model.MRegisterRequest;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserBusiness {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user;
        user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.toRegisterResponse(user);

    }

    public String login(MLoginRequest request) throws UserException {

        //Validate request

        //Verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(), user.getPassword())){
            throw UserException.loginFailPasswordIncorrect();
        }

        //TODO: generate JWT

        return "JWT TO DO";

    }

    public String uploadProfilePicture(MultipartFile file) throws FileException {
        //Validate file
        if (file == null) {
            throw FileException.fileNull();
        }

        //Validate size
        if (file.getSize() > 1048576 * 2) {
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw FileException.unsupported();
        }

        //Validate file type
        List<String> supportedType = Arrays.asList("image/jpeg", "image/png");
        if (!supportedType.contains(contentType)) {
            throw FileException.unsupported();
        }

        return "";
    }

}
