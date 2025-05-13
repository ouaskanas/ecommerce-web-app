package com.ouaskanas.commerce.mapper;
import com.ouaskanas.commerce.dto.request.RegisterDto;
import com.ouaskanas.commerce.dto.response.UserDto;
import com.ouaskanas.commerce.model.User;
import org.mapstruct.factory.Mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto UserDtoToUserDto(User user);
    User UserDtoToUser(UserDto userDto);
}
