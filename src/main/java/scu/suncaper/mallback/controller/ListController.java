
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

    @CrossOrigin
    @PostMapping("/api/list/dropGoodsById")
    @ResponseBody
    public Result dropById(@RequestBody Product productToDelete) {
        System.out.println("productToDelete is :");
        System.out.println(productToDelete);
        Integer pid = productToDelete.getPid();
        System.out.println("pid is :");
        System.out.println(pid);
        Product product = productService.getCertain(pid);
        System.out.println(product);
        if(product == null) {
            return ResultFactory.buildFailResult("商品不存在！");
        }else {
            System.out.print("商品存在");
            //删除商品
            //productService.dropGoodsById(pid);
            System.out.println("删除成功");
            return ResultFactory.buildSuccessResult(product.getPname());
        }
    }
}
