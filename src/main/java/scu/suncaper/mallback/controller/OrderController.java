
package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scu.suncaper.mallback.pojo.Order;
//import scu.suncaper.mallback.pojo.UserProduct;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.OrderService;
import scu.suncaper.mallback.service.ProductService;

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
}
