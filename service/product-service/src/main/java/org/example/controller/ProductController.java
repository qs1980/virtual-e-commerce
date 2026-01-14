package org.example.controller;

import org.example.common.Result;
import org.example.entity.ProductEntity;
import org.example.mapper.ProductMapper;
import org.example.service.ProductService;
import org.example.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/page")
    public Result<List<ProductVO>> getProductPage(@RequestParam("page") Integer pageNum,
                                                  @RequestParam("size") Integer size,
                                                  @RequestParam(value = "kay", defaultValue = "") String kay) {
        return productService.getProductPage(pageNum,size,kay);
    }
}
