package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findByAid(int aid);

    Admin findByAname(String aname);

    Admin getByAnameAndPassword(String aname, String password);

    @Query(nativeQuery = true, value = "SELECT a.aid, a.aname, a.password, a.phone from `admin` a")
    List<Object[]> findAllExceptsalt();
}
