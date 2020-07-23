package scu.suncaper.mallback.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.w3c.dom.UserDataHandler;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Order;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    @Modifying
    List<Order> getAllByPid(Integer pid);

    @Modifying
    List<Order> getAllByUid(Integer uid);

    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` where product.pid=`order` .pid and `order`.uid=1",nativeQuery = true)
    List<List<String>> getUserOrder(Integer cuid);

    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=0 and deliver_or_not=0",nativeQuery = true)
    List<List<String>> getUserOrder1(Integer cuid);

    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=1 and deliver_or_not=0",nativeQuery = true)
    List<List<String>> getUserOrder2(Integer cuid);

    @Query(value = "select oid,pname,trade_num,price,trade_num*price,trade_time,pay_or_not,deliver_or_not from product,`order` " +
            "where product.pid=`order` .pid and `order`.uid=1 and pay_or_not=1 and deliver_or_not=1",nativeQuery = true)
    List<List<String>> getUserOrder3(Integer cuid);

    void deleteByOid(Integer oid);
}
