package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.suncaper.mallback.dao.OrderDAO;
import scu.suncaper.mallback.dao.ProductDAO;
import scu.suncaper.mallback.dao.UserDAO;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.pojo.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    ProductDAO productDAO;
    User user = new User();
    Product product = new Product();

    //    @Transactional
//    public void addOrder() {
//        user.getProducts().add(product);
//        userDAO.save(user);
//        productDAO.save(product);
//    }

    //查询卖家售卖的所有订单
    public List<Object[]> getAllOrders(Integer sid) {
        Integer defaultSip = 123;

        List<Object[]> products = productDAO.findByNameContaining(defaultSip);
        for (Object[] pro : products) {
            for (Object object : pro) {
                System.out.print(object + ", ");
            }
            System.out.println();
        }
        return products;
    }
//    public Order  getCertain(Integer  pid) {
//        return orderDAO.getByPid(pid);
//    }
//
//    @Transactional
//    public void dropGoodsById(Integer productToDelete){
//        orderDAO.deleteByPid(productToDelete);
//    }

    public void  deleteCertain(Integer  oid) {
        orderDAO.deleteByOid(oid);
    }
    public List<List<String>> getUserOrder(int cuid){
        return orderDAO.getUserOrder(cuid);
    }
    public List<List<String>> getUserOrder1(int cuid){
        return orderDAO.getUserOrder1(cuid);
    }

    public List<List<String>> getUserOrder2(int cuid){
        return orderDAO.getUserOrder2(cuid);
    }

    public List<List<String>> getUserOrder3(int cuid){
        return orderDAO.getUserOrder3(cuid);
    }
}
