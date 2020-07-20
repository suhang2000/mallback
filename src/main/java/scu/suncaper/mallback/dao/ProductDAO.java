package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import scu.suncaper.mallback.pojo.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    Product findByPid(String pid);
    Product getByPidAndSid(String pid, String sid);

}
