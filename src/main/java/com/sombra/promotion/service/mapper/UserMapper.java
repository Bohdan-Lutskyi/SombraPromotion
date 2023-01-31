package com.sombra.promotion.service.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sombra.promotion.domain.Student;
import com.sombra.promotion.domain.User;
import com.sombra.promotion.domain.enumeration.UserRole;
import com.sombra.promotion.dto.StudentDTO;
import com.sombra.promotion.dto.UserDTO;
import com.sombra.promotion.util.JacksonUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
    @Override
    @Mapping(target = "userRoles", source = "userRoles", qualifiedByName = "serializeUserRoles")
    User toEntity(UserDTO dto);

    @Override
    @Mapping(target = "userRoles", source = "userRoles", qualifiedByName = "deserializeUserRoles")
    UserDTO toDto(User entity);

    default User fromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

    @Named("serializeUserRoles")
    default String serializeUserRoles(final Set<UserRole> userRoles) {
        return JacksonUtil.serialize(userRoles);
    }

    @Named("deserializeUserRoles")
    default Set<UserRole> deserializeUserRoles(final String userRoles) {
        return JacksonUtil.deserialize(userRoles, new TypeReference<Set<UserRole>>() {
        });
    }
}
