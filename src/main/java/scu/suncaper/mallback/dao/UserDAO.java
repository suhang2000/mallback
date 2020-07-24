package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import scu.suncaper.mallback.pojo.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUname(String uname);

    User getByUnameAndPassword(String uname, String password);

    User findByUnameAndPhone(String uname, String phone);
// woc自带findAll()
}
