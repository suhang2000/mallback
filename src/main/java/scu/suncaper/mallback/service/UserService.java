package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.dao.UserDAO;
import scu.suncaper.mallback.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User findByUname(String uname) { return userDAO.findByUname(uname);}

    public User findByUid(Integer uid) { return  userDAO.findByUid(uid);}

    public User get(String uname, String password) {
        return userDAO.getByUnameAndPassword(uname, password);
    }

    public User findByUnameAndPhone(String uname, String phone) { return userDAO.findByUnameAndPhone(uname, phone);}

    public void save(User user) { userDAO.save(user);}

    public boolean isExist(String uname) {
        User user = userDAO.findByUname(uname);
        return null != user;
    }

    public int userRegister(User user) {
        String uname = user.getUname();
        uname = HtmlUtils.htmlEscape(uname);
        user.setUname(uname);
        if (isExist(uname)) {
            return 2;
        }
        String password = user.getPassword();
        String phone = user.getPhone();
        String email = user.getEmail();
        String address = user.getAddress();
        String icon = user.getIcon();
        String gender = user.getGender();
        Date birthday = user.getBirthday();

        user.setPassword(HtmlUtils.htmlEscape(password));
        user.setPhone(HtmlUtils.htmlEscape(phone));
        user.setEmail(HtmlUtils.htmlEscape(email));
        user.setAddress(HtmlUtils.htmlEscape(address));
        user.setGender(HtmlUtils.htmlEscape(gender));
        user.setBirthday(birthday);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            user.setRegister_time(sdf.parse(sdf.format(new Date())));
        }catch (ParseException e){
            e.printStackTrace();
        }
        user.setIcon(HtmlUtils.htmlEscape(icon));
        if (uname.equals("") || password.equals("") || phone.equals("")) {
            return 0;
        }
        userDAO.save(user);
        return 1;
    }

    public List<Object[]> getAllUsers() { return userDAO.findAllExceptpassword(); }

    public void deleteByUid(Integer uid) { userDAO.deleteByUid(uid);}
}
