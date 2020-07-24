
package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.ProductService;

import java.util.List;
import java.util.Objects;

@RestController
public class ListController {
    @Autowired
    ProductService productService;

    @CrossOrigin
    @PostMapping("/api/list/product")
    @ResponseBody
    public List<Product> list(@RequestBody Product productToShow) {
        System.out.println("productToShow is :");
        System.out.println(productToShow);
        String pname = productToShow.getPname();
        System.out.println("pname is :");
        System.out.println(pname);
        List<Product> products = productService.get(pname);
        System.out.println(products);
        return products;
    }


}
