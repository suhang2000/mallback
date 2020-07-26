package scu.suncaper.mallback.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    public List<List> list(@RequestBody String unameToShow) {
        JSON uname = com.alibaba.fastjson.JSONObject.parseObject(unameToShow);
        String name = ((JSONObject) uname).getString("myName");
        return cartService.getCart(name);
    }

    //把购物车里相同商品全部记录都删除
    @CrossOrigin
    @PostMapping("/api/cart/dropGoods")
    @ResponseBody
    public Result dropById(@RequestBody Cart cartToDelete) {
        Integer cid = cartToDelete.getCid();
        Cart cart = cartService.getCertain(cid);
        if(cart == null) {
            return ResultFactory.buildFailResult("商品不存在！");
        }else {
            cartService.dropGoodsById(cid);
            return ResultFactory.buildSuccessResult(cart.getCid());
        }
    }

    //添加到购物车
    @CrossOrigin
    @PostMapping("/api/list/addCart")
    @ResponseBody
    public Result addCartByPid(@RequestBody String unameToShow) {
        JSON user = com.alibaba.fastjson.JSONObject.parseObject(unameToShow);
        String name = ((JSONObject) user).getString("myName");
        String pidTemp = ((JSONObject) user).getString("pid");
        int pid = Integer.parseInt(pidTemp);
        int count = cartService.getCount(name);
        Product product = productService.getCertain(pid);
        if(count<10){
            if(cartService.boo(pid,name)) {
                cartService.updateCart(pid,name);
            } else {
                cartService.insertCart(pid,name);
            }
            return ResultFactory.buildSuccessResult(product.getPname());
        }else return ResultFactory.buildFailResult("购物车已满，请结算后继续添加商品");
    }

    //添加商品数量
    @CrossOrigin
    @PostMapping("/api/cart/addGoods")
    @ResponseBody
    public Result addGoods(@RequestBody Cart goodToAdd) {
        Integer cid = goodToAdd.getCid();
        cartService.addGoods(cid);
        return ResultFactory.buildSuccessResult(cid);

    }

    //删除商品数量
    @CrossOrigin
    @PostMapping("/api/cart/removeGoods")
    @ResponseBody
    public Result removeGoods(@RequestBody Cart goodToRemove) {
        Integer cid = goodToRemove.getCid();
        cartService.removeGoods(cid);
        Integer proNum = cartService.cartProNum(cid);
        if(proNum==0){
            cartService.dropGoodsById(cid);
        }
        return ResultFactory.buildSuccessResult(cid);

    }
}
