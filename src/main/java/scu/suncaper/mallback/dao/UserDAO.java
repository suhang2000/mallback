package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import scu.suncaper.mallback.pojo.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUname(String uname);

    User findByUid(Integer uid);

    User getByUnameAndPassword(String uname, String password);

    User findByUnameAndPhone(String uname, String phone);

    @Query(nativeQuery = true, value = "SELECT u.uid, u.uname, u.phone, u.email, u.address, u.gender, u.icon, u.birthday, u.register_time from `user` u")
    List<Object[]> findAllExceptpassword();

    void deleteByUid(Integer uid);
}
