
package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scu.suncaper.mallback.pojo.Order;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.OrderService;
import scu.suncaper.mallback.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @CrossOrigin
    @PostMapping("/api/apple")
    @ResponseBody
    public List<Object[]> list(@RequestBody Order orderToShow) {
        System.out.println("下午好");
        System.out.println("orderToShow is :");
        System.out.println(orderToShow);
        Integer sid =123 ;
        List<Object[]> AllOrders = orderService.getAllOrders(sid);
        System.out.println(AllOrders);

        return AllOrders;
    }

    @CrossOrigin
    @PostMapping("/api/cart/deleteUserOrder")
    @ResponseBody
    public void dropById(@RequestBody Order orderToDelete) {
        System.out.println("productToDelete is :");
        System.out.println(orderToDelete);
        Integer oid = orderToDelete.getOid();
        System.out.println("pid is :");
        System.out.println(oid);
        orderService.deleteCertain(oid);
       /*System.out.println(order);
        if(order == null) {
            return ResultFactory.buildFailResult("商品不存在！");
        }else {
            System.out.print("商品存在");
            //删除商品
            //productService.dropGoodsById(pid);
            System.out.println("删除成功");
            return ResultFactory.buildSuccessResult(order.getOid());
        }*/
    }


    @CrossOrigin
    @PostMapping("/api/userorder/view")
    @ResponseBody
    public List<List<String>> view() {
        List<List<String>> orders= orderService.getUserOrder(1);
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).get(7).equals("1")){
                orders.get(i).add(8,"已发货");
            }else{
                if(orders.get(i).get(6).equals("0")){
                    orders.get(i).add(8,"未支付");
                }else if(orders.get(i).get(6).equals("1")){
                    orders.get(i).add(8,"待发货");
                }
            }
        }
        return orders;
    }

    @CrossOrigin
    @PostMapping("/api/userorder/view1")
    @ResponseBody
    public List<List<String>> view1() {
        List<List<String>> orders= orderService.getUserOrder1(1);
        for(int i=0;i<orders.size();i++){
            orders.get(i).add(8,"未支付");
        }
        return orders;
    }

    @CrossOrigin
    @PostMapping("/api/userorder/view2")
    @ResponseBody
    public List<List<String>> view2() {
        List<List<String>> orders= orderService.getUserOrder2(1);
        for(int i=0;i<orders.size();i++){
            orders.get(i).add(8,"未发货");
        }
        return orders;
    }
    @CrossOrigin
    @PostMapping("/api/userorder/view3")
    @ResponseBody
    public List<List<String>> view3() {
        List<List<String>> orders= orderService.getUserOrder3(1);
        for(int i=0;i<orders.size();i++){
            orders.get(i).add(8,"已发货");
        }
        return orders;
    }


}
