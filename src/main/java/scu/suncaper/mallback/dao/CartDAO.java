package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Cart;

import javax.transaction.Transactional;
import java.util.List;

public interface CartDAO extends JpaRepository<Cart, Integer> {
    List<Cart> getAllByCuid(Integer cuid);

    @Query(value = "select cid,pname, price,cart.number,(price*cart.number) from product,cart where pid=cart.cpid and cart.cuid=1",nativeQuery = true)
    List<List> getCart(Integer cuid);

    Cart getAllByCuidAndCpid(Integer Cuid, Integer Cpid);

    @Query(value = "select * from cart where cart.cpid=?1 and cart.cuid=?2",nativeQuery = true)
    Cart booCart(Integer pid, Integer cuid);

    @Query(value = "select product.number from product,cart where product.pid=cart.pid and cart.cid = ?1",nativeQuery = true)
    int proNumber(int cid);

    @Query(value = "select number from cart where cid=?1",nativeQuery = true)
    int cartProNumber(int cid);


    @Transactional
    @Modifying
    @Query(value = "update cart set number=number+1",nativeQuery = true)
    void addGoods();

    @Transactional
    @Modifying
    @Query(value = "update cart set number=number-1",nativeQuery = true)
    void removeGoods();

    @Modifying
    void deleteByCid(Integer cid);

    @Transactional
    @Modifying
    @Query(value = "insert into cart(cpid,cuid,number) values(?1,?2,?3)",nativeQuery = true)
    void insertCart(Integer pid,Integer uid,Integer number_1);

    @Transactional
    @Modifying
    @Query(value = "update cart set cpid=?1 , cuid=?2 , number=?3+number where cart.cpid=?1 and cart.cuid=?2",nativeQuery = true)
    void updateCart(Integer pid,Integer uid,Integer number_1);

    Cart getByCid(Integer cid);
}
