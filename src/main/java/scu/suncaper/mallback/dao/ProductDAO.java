package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Product;

import java.util.List;
import java.util.Set;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Modifying
    void deleteByPid(Integer pid);
    Product getByPid(Integer pid);
    Set<Product> getAllBySid(Integer sid);
    List<Product> getAllByPname(String pname);

    @Query(nativeQuery = true, value = "SELECT p.pid,p.sid, p.pname ,p.price,p.number,p.description" +
            " from `user` u, product p, `order` o, saler s"
            + " where p.sid = s.sid and s.sname = ?1 GROUP BY p.pid")
    List<Product> findBySname(String sname);

}
