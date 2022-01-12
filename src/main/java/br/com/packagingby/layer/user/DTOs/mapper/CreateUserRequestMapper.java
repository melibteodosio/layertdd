package br.com.packagingby.layer.user.DTOs.mapper;

import br.com.packagingby.layer.user.DTOs.CreateUserRequest;
import br.com.packagingby.layer.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateUserRequestMapper {
    CreateUserRequestMapper INSTANCE = Mappers.getMapper(CreateUserRequestMapper.class);

    User createRequestToUser(CreateUserRequest createUserRequest);

    CreateUserRequest userToUpdateRequest(User user);
}
