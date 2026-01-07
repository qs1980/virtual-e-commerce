package org.example.converter;

import org.example.dto.UserRegisterDTO;
import org.example.entity.UserEntity;
import org.example.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConverter {

    // DTO -> Entity
    UserEntity dtoToEntity(UserRegisterDTO dto);

    // Entity -> VO
    @Mapping(target = "inStock", expression = "java(entity.getStock() > 0)") // 示例
    UserVO entityToVO(UserEntity entity);

}
