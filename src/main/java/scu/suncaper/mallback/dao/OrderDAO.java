package scu.suncaper.mallback.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.w3c.dom.UserDataHandler;
import scu.suncaper.mallback.pojo.Order;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    @Modifying
    List<Order> getAllByPid(Integer pid);

    void deleteByOid(Integer oid);
}
