package scu.suncaper.mallback.dao;

import scu.suncaper.mallback.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findByAid(int aid);

    Admin findByAname(String aname);

    Admin getByAnameAndPassword(String aname, String password);
}
