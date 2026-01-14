package org.example.service;

import org.example.common.Result;
import org.example.vo.ProductVO;

import java.util.List;

public interface ProductService {
    Result<List<ProductVO>> getProductPage(Integer pageNum, Integer size,String kay);
}
