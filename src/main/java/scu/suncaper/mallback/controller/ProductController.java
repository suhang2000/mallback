package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
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
    @CrossOrigin
    @PostMapping("/api/list/addGoods")
    @ResponseBody
    public void addProduct(@RequestBody Product newProduct){
        productService.addProduct(newProduct);
    }
    @CrossOrigin
    @PostMapping("/api/list/dropGoodsById")
    @ResponseBody
    public Result dropById(@RequestBody Product productToDelete) {
        Integer pid = productToDelete.getPid();
        Product product = productService.getCertain(pid);
        if(product == null) {
            return ResultFactory.buildFailResult("商品不存在！");
        }else {
            //删除商品
            productService.dropGoodsById(pid);
            return ResultFactory.buildSuccessResult(product.getPname());
        }
    }

    @CrossOrigin
    @PostMapping("/api/home/product/info")
    public void saveProduct(@RequestBody Product product) {
        Product product1 = productService.getCertain(product.getPid());
        product.setSid(product1.getSid());
        productService.save(product);
    }
}
