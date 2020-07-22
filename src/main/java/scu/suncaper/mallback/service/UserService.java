package scu.suncaper.mallback.service;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import scu.suncaper.mallback.dao.UserDAO;
        import scu.suncaper.mallback.pojo.User;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User findByUname(String uname) { return userDAO.findByUname(uname);}

    public User get(String uname, String password) {
        return userDAO.getByUnameAndPassword(uname, password);
    }
}
