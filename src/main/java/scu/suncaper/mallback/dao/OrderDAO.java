package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Order;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    @Modifying
    List<Order> getAllByPid(Integer pid);

    @Modifying
    List<Order> getAllByUid(Integer uid);


    //用户添加未支付订单
    @Transactional
    @Modifying
    @Query(value = "insert into `order`(uid,pid,trade_time,trade_num,address,pay_or_not,deliver_or_not) " +
            "values ((select cuid from cart where cid=?1),(select cpid from cart where cid=?1 ),?3,(select number from cart where cid=?1 )," +
            "(select address from user where user.uid=?2 ),0,0) " ,nativeQuery = true)
    void addOrderPay1(Integer cid,Integer uid,String date);

    //用户添加已支付订单
    @Transactional
    @Modifying
    @Query(value = "insert into `order`(uid,pid,trade_time,trade_num,address,pay_or_not,deliver_or_not) " +
            "values ((select cuid from cart where cid=?1),(select cpid from cart where cid=?1 ),?3,(select number from cart where cid=?1 )," +
            "(select address from user where user.uid=?2 ),1,0)",nativeQuery = true)
    void addOrderPay2(Integer cid,Integer uid,String date);

    //用户支付“未支付订单”
    @Transactional
    @Modifying
    @Query(value = "update `order` set pay_or_not=1 and deliver_or_not=0  where oid=?1",nativeQuery = true)
    void orderPay(Integer oid);

    //用户支付的时候product商品数量-相应的数量
    @Transactional
    @Modifying
    @Query(value = "update product set number=number-?2 where pid=?1",nativeQuery = true)
    void orderProDrop(Integer pid,Integer number);

    //用户撤回已支付订单的时候product商品数量+相应的数量
    @Transactional
    @Modifying
    @Query(value = "update product set number=number+?2 where pid=?1",nativeQuery = true)
    void orderProPlus(Integer pid,Integer number);


    //用户删去购物车内的记录
    @Transactional
    @Modifying
    @Query(value = "delete from cart where cid=?1",nativeQuery = true)
    void dropCartOrder(Integer cid);

    //用户从未支付界面撤回订单
    @Transactional
    @Modifying
    @Query(value = "delete from `order` where oid=?1",nativeQuery = true)
    void dropOrder_unpaid(Integer oid);

    //根据cid找到商品的库存
    @Query(value = "select product.number " +
            "from product,cart " +
            "where cart.cpid=product.pid and cid =?1",nativeQuery = true)
    int orderProNumber(Integer cid);

    //根据cid找到当前商品数量
    @Query(value = "select number from cart where cid =?1",nativeQuery = true)
    int orderCartNumber(Integer cid);

    //根据oid找到商品库存
    @Query(value="select product.number from product,`order` " +
            "where `order`.pid=product.pid and oid =?1",nativeQuery = true)
    int orderProNumber1(Integer oid);

    //根据oid返回当前订单内商品数量
    @Query(value = "select trade_num from `order` where `order`.oid=?1",nativeQuery = true)
    int orderNumber(Integer id);

    //根据oid返回pid
    @Query(value = "select pid from `order` where `order`.oid=?1",nativeQuery = true)
    int orderPid(Integer pid);

    //根据cid返回pid
    @Query(value = "select cpid from cart where cart.cid=?1",nativeQuery = true)
    int cartPid(Integer cid);

    //用户全部订单
    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not " +
            "from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1",nativeQuery = true)
    List<List<String>> getUserOrder(Integer cuid);

    //用户未支付订单的list返回
    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=0 and deliver_or_not=0",nativeQuery = true)
    List<List> getUserOrder_list(Integer cuid);

    //用户待发货订单的list返回
    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=1 and deliver_or_not=0",nativeQuery = true)
    List<List> getUserOrder2_list(Integer cuid);

    //用户未支付订单
    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=0 and deliver_or_not=0",nativeQuery = true)
    List<List<String>> getUserOrder1(Integer cuid);

    //用户待发货订单
    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=1 and deliver_or_not=0",nativeQuery = true)
    List<List<String>> getUserOrder2(Integer cuid);

    //用户待收货订单
    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=1 and deliver_or_not=1",nativeQuery = true)
    List<List<String>> getUserOrder3(Integer cuid);



    void deleteByOid(Integer oid);

    @Query(nativeQuery = true, value = "SELECT p.pid, p.pname ,o.trade_time,o.trade_num ,o.address,o.pay_or_not," +
            " o.deliver_or_not,u.uname,s.sname from `user` u, product p, `order` o, saler s"
            + " where u.uid = o.uid and p.pid = o.pid and p.sid = s.sid and p.sid = ?1 group by p.pid, p.pname")
    List<Object[]> findBySidContaining(Integer sid);

    @Query(nativeQuery = true, value = "SELECT p.pid, p.pname ,o.trade_time,o.trade_num ,o.address,o.pay_or_not," +
            " o.deliver_or_not,u.uname,s.sname from `user` u, product p, `order` o, saler s"
            + " where u.uid = o.uid and p.pid = o.pid and p.sid = s.sid and p.pname = ?1 group by p.pid, p.pname")
    List<Object[]> findByPnameContaining(String pname);

    @Query(nativeQuery = true, value = "SELECT p.pid, p.pname ,o.trade_time,o.trade_num ,o.address,o.pay_or_not," +
            " o.deliver_or_not,u.uname,s.sname from `user` u, product p, `order` o, saler s"
            + " where u.uid = o.uid and p.pid = o.pid and p.sid = s.sid and u.uname = ?1 group by p.pid, p.pname")
    List<Object[]> findByUnameContaining(String uname);

    @Query(nativeQuery = true, value = "SELECT p.pid, p.pname ,o.trade_time,o.trade_num ,o.address,o.pay_or_not," +
            " o.deliver_or_not,u.uname,s.sname from `user` u, product p, `order` o, saler s"
            + " where u.uid = o.uid and p.pid = o.pid and p.sid = s.sid and s.sname= ?1 group by p.pid, p.pname")
    List<Object[]> findBySnameContaining(String sname);
}
