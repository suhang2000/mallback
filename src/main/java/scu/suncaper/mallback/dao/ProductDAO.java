package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.pojo.UserProduct;

import java.util.List;
import java.util.Set;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Modifying
    void deleteByPid(Integer pid);
    Product getByPid(Integer pid);
    Product getBySid(Integer sid);
    Set<Product> getAllBySid(Integer sid);
    List<Product> getAllByPname(String pname);
    //关联查询    @Query可以用于自定义sql语句,如果是修改需加上@Modifying ,这是一条关联查询语句,返回的结果映射到新对象中，新对象为接口，里面含有对应参数的构造方法。
    @Query(nativeQuery = true, value = "SELECT p.pid, p.pname ,o.trade_time,o.trade_num ,o.address,o.pay_or_not, o.deliver_or_not from `user` u, product p, `order` o"
            + " where u.uid = o.uid and p.pid = o.pid and p.sid = ?1 group by p.pid, p.pname")
    List<Object[]> findByNameContaining(Integer sid);
}
