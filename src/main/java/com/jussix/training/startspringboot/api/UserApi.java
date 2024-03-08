package com.jussix.training.startspringboot.api;

import com.jussix.training.startspringboot.entity.User;
import com.jussix.training.startspringboot.exception.FileException;
import com.jussix.training.startspringboot.exception.UserException;
import com.jussix.training.startspringboot.model.MLoginRequest;
import com.jussix.training.startspringboot.model.MRegisterResponse;
import org.springframework.web.bind.annotation.*;

import com.jussix.training.startspringboot.business.UserBusiness;
import com.jussix.training.startspringboot.exception.BaseException;
import com.jussix.training.startspringboot.model.MRegisterRequest;
import com.jussix.training.startspringboot.model.TestResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")

public class UserApi {

    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException{
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException {
        String response = business.refreshToken();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response;
        response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
