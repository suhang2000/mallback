package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import scu.suncaper.mallback.pojo.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Modifying
    void deleteByPid(Integer pid);
    Product getByPid(Integer pid);
}
