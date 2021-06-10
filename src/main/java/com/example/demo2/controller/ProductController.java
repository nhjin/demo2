package com.example.demo2.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.demo2.entity.Flex;
import com.example.demo2.entity.FlexDetail;
import com.example.demo2.entity.Nutrition;
import com.example.demo2.entity.Product;
import com.example.demo2.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public String saveProduct(@RequestBody Product product) throws InterruptedException, ExecutionException {
        return productService.saveProduct(product);
    }

//    @GetMapping("/products/{value}/{qwer")
//    public List<Flex> getAllProduct(@PathVariable String value) throws InterruptedException, ExecutionException{
//
//        return productService.getProducts(value);
//    }

    @GetMapping("/products/{id}")
    public FlexDetail getProductDetails(@PathVariable String id) throws InterruptedException, ExecutionException {

        return productService.getProductDetails(id);
    }

    @GetMapping("/products/list/{category}/{id}")
    public List<Flex> getProductTest(@PathVariable String category, @PathVariable int id) throws InterruptedException, ExecutionException {


        return productService.getProductTest(category, id);
    }

    @GetMapping("/calc/{car}/{pro}/{fat}")
    public List<Nutrition> getTest(@PathVariable float car, @PathVariable float pro, @PathVariable float fat) throws InterruptedException, ExecutionException {


        return productService.getTest(car, pro, fat);
    }
}
