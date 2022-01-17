package ru.dreadblade.czarbank.api.mapper.security;

import org.mapstruct.Mapper;
import ru.dreadblade.czarbank.api.model.request.security.UserRequestDTO;
import ru.dreadblade.czarbank.api.model.response.security.UserResponseDTO;
import ru.dreadblade.czarbank.domain.security.User;

@Mapper(uses = { RoleMapper.class })
public interface UserMapper {
    User requestDtoToEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO entityToResponseDto(User user);
}
