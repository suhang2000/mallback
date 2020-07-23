package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scu.suncaper.mallback.pojo.Cart;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.CartService;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.pojo.User;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.service.ProductService;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @CrossOrigin
    @PostMapping("/api/cart/view")
    @ResponseBody
    public List<Cart> list() {
        List<Cart> carts = cartService.get();
        return carts;
    }

    @CrossOrigin
    @PostMapping("/api/list/addCart")
    @ResponseBody
    public Result addCartById(@RequestBody Product productToAddCart) {
        Integer pid = productToAddCart.getPid();
        System.out.print(pid+"\n");
        Product product = productService.getCertain(pid);
        System.out.print(product+"\n");
        if(cartService.boo(product.getPid(),1)) {
            cartService.updateCart(product.getPid(),1,1);
        } else {
            cartService.insertCart(product.getPid(),1,1);
        }
        System.out.println("增加成功");
        return ResultFactory.buildSuccessResult(product.getPname());

    }


    @CrossOrigin
    @PostMapping("/api/cart/add")
    @ResponseBody
    public Result addById(@RequestBody Cart cartToAdd) {
        Integer cid = cartToAdd.getCid();
        System.out.print(cid+"\n");
        Cart cart = cartService.getCertain(cid);
        System.out.print(cart+"\n");
        cartService.addGoodsById(cid);
        System.out.println("添加成功");
        return ResultFactory.buildSuccessResult(cart.getCid());

    }
}
