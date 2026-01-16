package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.Result;
import org.example.converter.ProductConverter;
import org.example.entity.ProductEntity;
import org.example.mapper.ProductMapper;
import org.example.service.ProductService;
import org.example.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductConverter productConverter;
    @Autowired
    private ProductMapper productMapper;
    @Cacheable(value = "productPage", key = "#pageNum + '_' + #size + '_' + (#kay == null ? '' : #kay)")
    @Override
    public Result<List<ProductVO>> getProductPage(Integer pageNum, Integer size,String kay) {
        // 1. 创建分页对象
        Page<ProductEntity> page = new Page<>(pageNum, size);
        // 2. 构建查询条件
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();
        if (kay != null && !kay.isEmpty()) {
            wrapper.like("product_name", kay); // 模糊查询，假设数据库字段是 product_name
        }
        // 3. 分页查询
        IPage<ProductEntity> productPage = productMapper.selectPage(page, wrapper);

        // 4. 将实体转成 VO
        List<ProductVO> productVOList = productPage.getRecords().stream().map(entity -> {
            return productConverter.entityToVO(entity);
        }).collect(Collectors.toList());

        // 5. 返回结果
        return Result.ok(productVOList);
    }
}

