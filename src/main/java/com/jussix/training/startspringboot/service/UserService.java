package com.jussix.training.startspringboot.service;

import com.jussix.training.startspringboot.entity.User;
import com.jussix.training.startspringboot.exception.UserException;
import com.jussix.training.startspringboot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(String id){
        return repository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

    public boolean matchPassword(String rawPassword, String endcodedPassword){
        return passwordEncoder.matches(rawPassword, endcodedPassword);
    }

    public User create(String email, String password, String name) throws UserException {
        //Validate
        if(Objects.isNull(email)){
            throw UserException.createEmailNull();
        }

        if(Objects.isNull(password)){
            throw UserException.createPasswordNull();
        }

        if(Objects.isNull(name)){
            throw UserException.createNameNUll();
        }

        //Verify
        if(repository.existsByEmail(email)){
            throw UserException.createEmailDuplicated();
        }

        //Save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);

        return repository.save(entity);
    }

    public User update(User user){
        return repository.save(user);
    }

    public User updateName(String id, String name) throws UserException {
        Optional<User> opt = repository.findById(id);
        if(opt.isEmpty()){
            throw UserException.notFound();
        }

        User user = opt.get();
        user.setName(name);

        return repository.save(user);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

}
