package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.suncaper.mallback.dao.OrderDAO;
import scu.suncaper.mallback.dao.ProductDAO;
import scu.suncaper.mallback.dao.UserDAO;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.pojo.User;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    ProductDAO productDAO;

    public List<Object[]> findAllOrders() {
        //管理人员用，用以获取所有订单
        return orderDAO.findAllOrders();
    }
    public List<Object[]> getOrdersBySname(String sname){
        return orderDAO.findBySnameContaining(sname);
    }
    public List<Object[]> getOrdersByPname(String pname){
        return orderDAO.findByPnameContaining(pname);
    }
    public List<Object[]> getOrdersByUname(String uname){
        return orderDAO.findByUnameContaining(uname);
    }
    public List<Object[]> getOrdersByPnameAndSname(String pname, String sname){
        return orderDAO.findByPnameAndSname(pname, sname);
    }
    public List<Object[]> getOrdersByUnameAndSname(String uname, String sname){
        return orderDAO.findByUnameAndSname(uname, sname);
    }
    public  void deliverOrders(String oid){
        orderDAO.deliverOrders(oid);
    }
    //用户增加未支付订单
    @Transactional
    public void addOrderPay1(Integer cid, Integer uid){
        Date t = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date= df.format(t);
        orderDAO.addOrderPay1(cid,uid,date);
    }

    //用户增加已支付订单
    @Transactional
    public void addOrderPay2(Integer cid, Integer uid){
        Date t = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date= df.format(t);
        orderDAO.addOrderPay2(cid,uid,date);
    }

    //用户从增加已支付订单
    public void orderPay(Integer oid){
        orderDAO.orderPay(oid);
    }


    //用户删去购物车记录
    @Transactional
    public void dropCartOrder(Integer cid){
        orderDAO.dropCartOrder(cid);
    }

    //用户从未支付界面撤回订单
    @Transactional
    public void dropOrder_unpaid(Integer oid){
        orderDAO.dropOrderUnpaid(oid);
    }

    //返回商品数量-用户
    public Integer orderProNum(Integer cid){
        return orderDAO.orderProNumber(cid);
    }

    //返回购物车内商品数量-用户
    public Integer orderCartNum(Integer cid){
        return orderDAO.orderCartNumber(cid);
    }



    //删除by oid
    public void  deleteCertain(Integer  oid) {
        orderDAO.deleteByOid(oid);
    }

    //根据oid返回商品数量-用户
    public Integer orderProNum1(Integer oid){
        return orderDAO.orderProNumber1(oid);
    }
    //根据oid返回订单内商品数量-用户
    public Integer orderNumber(Integer oid){
        return orderDAO.orderNumber(oid);
    }

    //根据oid找到pid
    public Integer orderPid(Integer oid){
        return orderDAO.orderPid(oid);
    }

    //根据cid找到pid
    public Integer cartPid(Integer cid){
        return orderDAO.cartPid(cid);
    }

    //根据id，支付的时候商品数量-1
    public void orderProDrop(Integer pid,Integer number){
        orderDAO.orderProDrop(pid,number);
    }

    //撤回已支付订单的时候商品数量+相应数量
    public void orderProPlus(Integer pid,Integer number){
        orderDAO.orderProPlus(pid,number);
    }

    //用户查看全部订单
    public List<List<String>> getUserOrder(String name){
        return orderDAO.getUserOrder(name);
    }

    //用户查看未支付订单
    public List<List<String>> getUserOrder1(String name){
        return orderDAO.getUserOrder1(name);
    }

    //用户查看未支付订单-list
    public List<List> getUserOrder_list(String name){
        return orderDAO.getUserOrderList(name);
    }

    //用户查看待发货订单-list
    public List<List> getUserOrder2_list(String name){
        return orderDAO.getUserOrder2List(name);
    }

    //用户查看待发货订单
    public List<List<String>> getUserOrder2(String name){
        return orderDAO.getUserOrder2(name);
    }

    //用户查看待收货订单
    public List<List<String>> getUserOrder3(String name){
        return orderDAO.getUserOrder3(name);
    }
}
