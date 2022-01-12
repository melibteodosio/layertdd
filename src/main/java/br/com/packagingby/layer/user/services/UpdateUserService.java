package br.com.packagingby.layer.user.services;

import br.com.packagingby.layer.beans.ObjectMapper;
import br.com.packagingby.layer.user.entities.User;
import br.com.packagingby.layer.user.repositories.UsersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService {
    private final UsersRepository usersRepository;
    private final ObjectMapper objectMapper;

    public User updateUser(@NonNull User userUpdateData) {
        User userExists;

        if (userUpdateData.getId() == 0L && StringUtils.isBlank(userUpdateData.getUsername())) {
            return null;
        }

        if (StringUtils.isBlank(userUpdateData.getName()) && StringUtils.isBlank(userUpdateData.getEmail())) {
            return null;
        }

        userExists = usersRepository.findById(userUpdateData.getId()).orElse(null);

        if(userExists == null) {
            userExists = usersRepository.findByUsername(userUpdateData.getUsername());
        }

        if (userExists != null) {
            User userToUpdate = (User) objectMapper.mapNonNullValues(userUpdateData, userExists);
            return usersRepository.save(userToUpdate);
        }

        return null;
    }

}
