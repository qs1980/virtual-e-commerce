package org.example.converter;


import org.example.dto.ProductDTO;
import org.example.entity.ProductEntity;

import org.example.vo.ProductVO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter {

    // DTO -> Entity
    ProductEntity dtoToEntity(ProductDTO dto);

    // Entity -> VO
    ProductVO entityToVO(ProductEntity entity);

}
