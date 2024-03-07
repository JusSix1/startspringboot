package com.jussix.training.startspringboot.mapper;

import com.jussix.training.startspringboot.entity.User;
import com.jussix.training.startspringboot.model.MRegisterResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}
