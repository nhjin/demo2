package com.example.demo2.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.demo2.entity.Flex;
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

    @GetMapping("/products/{value}")
    public List<Flex> getAllProduct(@PathVariable String value) throws InterruptedException, ExecutionException{

        return productService.getProducts(value);
    }

    @GetMapping("/products/{value}/{name}")
    public Flex getProduct(@PathVariable String value, @PathVariable String name) throws InterruptedException, ExecutionException{

        return productService.getProductDetails(value, name);
    }

    @GetMapping("/products/test/{category}/{id}")
    public List<Flex> getProductTest(@PathVariable String category, @PathVariable int id) throws InterruptedException, ExecutionException{

        return productService.getProductTest(category, id);
    }

}
