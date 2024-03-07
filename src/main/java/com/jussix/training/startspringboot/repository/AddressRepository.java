package com.jussix.training.startspringboot.repository;

import com.jussix.training.startspringboot.entity.Address;
import com.jussix.training.startspringboot.entity.Social;
import com.jussix.training.startspringboot.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);

}
