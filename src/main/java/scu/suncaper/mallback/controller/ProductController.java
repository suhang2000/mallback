package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @CrossOrigin
    @GetMapping("/api/home/products")
    public List<Product> productList() {
        return productService.getProducts();
    }
}
