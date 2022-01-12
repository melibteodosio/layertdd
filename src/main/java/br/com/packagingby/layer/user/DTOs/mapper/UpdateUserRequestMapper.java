package br.com.packagingby.layer.user.DTOs.mapper;

import br.com.packagingby.layer.user.DTOs.UpdateUserRequest;
import br.com.packagingby.layer.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateUserRequestMapper {
    UpdateUserRequestMapper INSTANCE = Mappers.getMapper(UpdateUserRequestMapper.class);

    User updateRequestToUser(UpdateUserRequest updateUserRequest);

    UpdateUserRequest userToUpdateRequest(User user);
}
