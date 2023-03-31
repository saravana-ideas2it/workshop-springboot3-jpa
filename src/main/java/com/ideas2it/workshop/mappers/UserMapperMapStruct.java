package com.ideas2it.workshop.mappers;

import com.ideas2it.workshop.entities.User;
import com.ideas2it.workshop.dtos.UserDto;
import com.ideas2it.workshop.dtos.UserGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface UserMapperMapStruct {
    UserMapperMapStruct INSTANCE = Mappers.getMapper( UserMapperMapStruct.class );
    @Mapping(source = "email", target = "emailAddress")
    UserDto userToUserPostDto(
            User user
    );

    @Mapping(source = "email", target = "emailAddress")
    UserGetDto userToUserGetDto(
            User user
    );

    @Mapping(source = "emailAddress", target = "email")
    User userDtoToUser(
            UserDto userDto
    );
}
