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
    Set<Product> getAllBySid(Integer sid);
    List<Product> getAllByPname(String pname);

}
