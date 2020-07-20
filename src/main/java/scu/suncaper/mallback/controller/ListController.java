
package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.service.ProductService;

import java.util.List;
import java.util.Objects;

@RestController
public class ListController {
    @Autowired
    ProductService productService;
    @CrossOrigin
    @GetMapping("/api/list/product")
    @ResponseBody
    public List<Product> list() {
        List<Product> products = productService.get();
        System.out.println(products);
        return products;

    }
}
