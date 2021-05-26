package com.example.demo2.controller;

import java.util.concurrent.ExecutionException;

import com.example.demo2.entity.Product;
import com.example.demo2.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public String saveProduct(@RequestBody Product product) throws InterruptedException, ExecutionException{
        return productService.saveProduct(product);
    }

    @GetMapping("/products/{name}")
    public Product getProduct(@PathVariable String name) throws InterruptedException, ExecutionException{

        return productService.getProductDetails(name);
    }

}
