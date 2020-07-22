package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.suncaper.mallback.dao.OrderDAO;
import scu.suncaper.mallback.dao.ProductDAO;
import scu.suncaper.mallback.pojo.Order;
import scu.suncaper.mallback.pojo.Product;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    ProductDAO productDAO;

    public List<Order> getAllOrders(Integer sid) {
        System.out.println(sid);
        List<Order> myOrder = new ArrayList<>();
        System.out.println(productDAO.getAllBySid(sid));

        for(Product product : productDAO.getAllBySid(sid)){
            System.out.println(product);
            myOrder.addAll(orderDAO.getAllByPid(product.getPid()));
        }
        return myOrder;
    }
//    public Order  getCertain(Integer  pid) {
//        return orderDAO.getByPid(pid);
//    }
//
//    @Transactional
//    public void dropGoodsById(Integer productToDelete){
//        orderDAO.deleteByPid(productToDelete);
//    }
}
