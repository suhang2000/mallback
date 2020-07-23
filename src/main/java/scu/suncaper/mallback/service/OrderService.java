package scu.suncaper.mallback.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import scu.suncaper.mallback.dao.OrderDAO;
import scu.suncaper.mallback.dao.ProductDAO;
import scu.suncaper.mallback.dao.UserDAO;
import scu.suncaper.mallback.pojo.Order;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.pojo.User;
import scu.suncaper.mallback.pojo.UserProduct;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;


@Service
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderService {
    @Autowired
    UserDAO userDAO;
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
    @Test
    public List<Object[]> getAllOrders(Integer sid) {
        Integer defaultSip = 123;
        assertEquals(productDAO.findAll().size(), 5);
        assertEquals(userDAO.findAll().size(), 2);

        List<Object[]> products = productDAO.findByNameContaining(defaultSip);
        for (Object[] pro : products) {
            for (Object object : pro) {
                System.out.print(object + ", ");
            }
            System.out.println();
        }
        return products;
    }
}