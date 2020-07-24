package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Product;
//import scu.suncaper.mallback.pojo.UserProduct;

import java.util.List;
import java.util.Set;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Modifying
    void deleteByPid(Integer pid);
    Product getByPid(Integer pid);
    Product getBySid(Integer sid);
    Set<Product> getAllBySid(Integer sid);
    List<Product> getAllByPname(String pname);

    @Query(nativeQuery = true, value = "SELECT p.pid, p.pname ,o.trade_time,o.trade_num ,o.address,o.pay_or_not," +
            " o.deliver_or_not,u.uname,s.snamee from `user` u, product p, `order` o, saler s"
            + " where u.uid = o.uid and p.pid = o.pid and p.sid = s.sid and p.sid = ?1 group by p.pid, p.pname")
    List<Object[]> findByNameContaining(Integer sid);
}
