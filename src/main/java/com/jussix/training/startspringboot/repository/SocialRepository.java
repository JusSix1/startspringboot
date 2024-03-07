package com.jussix.training.startspringboot.repository;

import com.jussix.training.startspringboot.entity.Social;
import com.jussix.training.startspringboot.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);

}
