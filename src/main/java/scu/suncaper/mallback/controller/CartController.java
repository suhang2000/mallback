package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scu.suncaper.mallback.pojo.Cart;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.CartService;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.service.ProductService;
import java.util.List;


@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    //本人购物车查看
    @CrossOrigin
    @PostMapping("/api/cart/view")
    @ResponseBody
    public List<List> list() {
        List<List> carts= cartService.getCart(1);
        System.out.println(carts);
        return carts;
    }

    //把购物车里相同商品全部记录都删除
    @CrossOrigin
    @PostMapping("/api/cart/dropGoods")
    @ResponseBody
    public Result dropById(@RequestBody Cart cartToDelete) {
        System.out.println("productToDelete is :");
        System.out.println(cartToDelete);
        Integer cid = cartToDelete.getCid();
        System.out.println("cid is :");
        System.out.println(cid);
        Cart cart = cartService.getCertain(cid);
        System.out.println(cart);
        if(cart == null) {
            return ResultFactory.buildFailResult("商品不存在！");
        }else {
            System.out.print("商品存在");
            //删除商品
            cartService.dropGoodsById(cid);
            System.out.println("删除成功");
            return ResultFactory.buildSuccessResult(cart.getCid());
        }
    }

    //添加到购物车，目前还是不能和前端连起来，参数传的时候出了点问题。。
    @CrossOrigin
    @PostMapping("/api/list/addCart")
    @ResponseBody
    public Result addCartById(@RequestBody Product productToAddCart) {
        System.out.println("成功");
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

    //添加商品数量
    @CrossOrigin
    @PostMapping("/api/cart/addGoods")
    @ResponseBody
    public Result addGoods(@RequestBody Cart goodToAdd) {
        Integer cid = goodToAdd.getCid();
        Cart cart = cartService.getCertain(cid);
        cartService.addGoods();
        System.out.println("添加成功");
        return ResultFactory.buildSuccessResult(cid);

    }

    //添加商品数量
    @CrossOrigin
    @PostMapping("/api/cart/removeGoods")
    @ResponseBody
    public Result removeGoods(@RequestBody Cart goodToRemove) {
        Integer cid = goodToRemove.getCid();
        Cart cart = cartService.getCertain(cid);
        cartService.removeGoods();
        System.out.println("删除成功");
        Integer proNum = cartService.cartProNum(cid);
        if(proNum==0){
            cartService.dropGoodsById(cid);
        }

        return ResultFactory.buildSuccessResult(cid);

    }
}
