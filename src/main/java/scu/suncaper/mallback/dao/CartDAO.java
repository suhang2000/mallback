package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Cart;

import javax.transaction.Transactional;
import java.util.List;

public interface CartDAO extends JpaRepository<Cart, Integer> {
    List<Cart> getAllByCuid(Integer cuid);

    //查看本人购物车全部商品
    @Query(value = "select cid,pname, price,cart.number,(price*cart.number) from product,cart,user " +
            "where pid=cart.cpid and cart.cuid=`user`.uid and `user`.uname=?1",nativeQuery = true)
    List<List> getCart(String uname);

    //Cart getAllByCuidAndCpid(Integer cuid, Integer cpid);

    @Query(value = "select * from cart,`user` where cart.cpid=?1 and cart.cuid=`user`.uid and `user`.uname=?2",nativeQuery = true)
    Cart booCart(Integer pid, String uname);

    //根据cid返回商品总库存
    @Query(value = "select product.number from product,cart where product.pid=cart.pid and cart.cid = ?1",nativeQuery = true)
    int proNumber(int cid);

    //根据cid返回当前购物车这条记录的商品的数量
    @Query(value = "select number from cart where cid=?1",nativeQuery = true)
    int cartProNumber(int cid);


    //在购物车内添加商品数量
    @Transactional
    @Modifying
    @Query(value = "update cart set number=number+1 where cid=?1",nativeQuery = true)
    void addGoods(Integer cid);

    //在购物车内减少商品数量
    @Transactional
    @Modifying
    @Query(value = "update cart set number=number-1 where cid=?1",nativeQuery = true)
    void removeGoods(Integer cid);

    @Modifying
    void deleteByCid(Integer cid);

    //添加到购物车功能
    @Transactional
    @Modifying
    @Query(value = "insert into cart(cpid,cuid,number) values(?1,(select `user`.uid from user where `user`.uname=?2),1)",nativeQuery = true)
    void insertCart(Integer pid,String name);

    //添加到购物车功能
    @Transactional
    @Modifying
    @Query(value = "update cart set number=1+number where cart.cpid=?1 and cuid=(select `user`.uid from `user` where `user`.uname=?2)",nativeQuery = true)
    void updateCart(Integer pid,String name);

    Cart getByCid(Integer cid);
}
