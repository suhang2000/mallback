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
    @GetMapping("/api/home/products")
    public List<Product> productList() {
        return productService.getProducts();
    }
    @CrossOrigin
    @PostMapping("/api/list/addGoods")
    @ResponseBody
    public void addProduct(@RequestBody Product newProduct){
        System.out.println("进入商品添加功能");
        System.out.println(newProduct);
        productService.addProduct(newProduct);
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
            productService.dropGoodsById(pid);
            System.out.println("删除成功");
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
//    @CrossOrigin
//    @PostMapping("/api/list/product/saler")
//    @ResponseBody
//    public Result AllProductsForSaler(@RequestBody String sname) {
//        System.out.println("productToDelete is :");
//        System.out.println(sname);sname
//        Product product = productService.getCertain(pid);
//        System.out.println(product);
//        if(product == null) {
//            return ResultFactory.buildFailResult("商品不存在！");
//        }else {
//            System.out.print("商品存在");
//            //删除商品
//            productService.dropGoodsById(pid);
//            System.out.println("删除成功");
//            return ResultFactory.buildSuccessResult(product.getPname());
//        }
//}
}
