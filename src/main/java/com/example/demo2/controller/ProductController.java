package com.example.demo2.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.demo2.entity.*;
import com.example.demo2.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.support.SessionStatus;

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

    @PostMapping("/saveUser")
    public String saveUserInfo(@RequestBody User user) throws InterruptedException, ExecutionException {
        return productService.saveUserInfo(user);
    }

    @PostMapping("/updateFavorite")
    public String updateFavorite(@RequestBody String favorite, HttpServletRequest request) throws InterruptedException, ExecutionException, UnsupportedEncodingException {
        String uid = (String) request.getSession().getAttribute("uid");
        String decodeFavorite = URLDecoder.decode(favorite, "UTF-8");
        String favoriteResult = decodeFavorite.replace("=", "");
        return productService.updateFavorite(uid, favoriteResult);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("uid");
        return "logout";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request) throws ExecutionException, InterruptedException {
        String uid = (String) request.getSession().getAttribute("uid");

        return productService.deleteUser(uid);
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

    @GetMapping("/products/search/{search}")
    public List<Flex> searchProduct(@PathVariable String search) throws InterruptedException, ExecutionException {

        return productService.searchProducts(search);
    }

    @GetMapping("/products/{category}/{id}")
    public Flex getProductCheck(@PathVariable String category, @PathVariable String id) throws InterruptedException, ExecutionException {

        return productService.getProductCheck(category, id);
    }

    @GetMapping("/calc/{car}/{pro}/{fat}")
    public List<Nutrition> getTest(@PathVariable float car, @PathVariable float pro, @PathVariable float fat) throws InterruptedException, ExecutionException {

        return productService.getTest(car, pro, fat);
    }
}
