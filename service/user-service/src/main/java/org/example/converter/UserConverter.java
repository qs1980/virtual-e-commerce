package org.example.converter;

import org.example.dto.UserRegisterDTO;
import org.example.dto.UserUpdateDTO;
import org.example.entity.UserEntity;
import org.example.vo.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {

    // DTO -> Entity
    UserEntity dtoToEntity(UserRegisterDTO dto);

    // Entity -> VO
    UserVO entityToVO(UserEntity entity);


    UserEntity dtoToEntity(UserUpdateDTO dto);

}
