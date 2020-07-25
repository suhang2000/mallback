package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Saler;

import java.util.List;

public interface SalerDAO extends JpaRepository<Saler, Integer> {
    Saler findBySname(String sname);

    Saler findBySid(Integer sid);

    Saler getBySnameAndPassword(String sname, String password);

    Saler findBySnameAndPhone(String sname, String phone);

    @Query(nativeQuery = true, value = "SELECT s.sid, s.sname, s.phone, s.email, s.address, s.bank_num, s.register_time from `saler` s")
    List<Object[]> findAllExceptpassword();

    void deleteBySid(Integer sid);
}
