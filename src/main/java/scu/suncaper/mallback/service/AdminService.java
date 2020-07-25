package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.dao.AdminDAO;
import scu.suncaper.mallback.pojo.Admin;

import java.security.SecureRandom;
import java.util.List;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;
    public Admin findByAid(int aid) {
        return adminDAO.findByAid(aid);
    }

    public Admin findByAname(String aname) {
        return adminDAO.findByAname(aname);
    }

    public Admin get(String aname, String password) {
        return adminDAO.getByAnameAndPassword(aname, password);
    }

    private boolean isExist(String aname) {
        Admin admin = adminDAO.findByAname(aname);
        return null != admin;
    }
    // 生成 20 位salt
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        return encodeBase64String(bytes);
    }

    public int adminRegister(Admin admin) {
        String aname = admin.getAname();
        String password = admin.getPassword();
        String phone = admin.getPhone();

        password = HtmlUtils.htmlEscape(password);
        admin.setPassword(password);
        aname = HtmlUtils.htmlEscape(aname);
        admin.setAname(aname);
        phone = HtmlUtils.htmlEscape(phone);
        admin.setPhone(phone);

        if (aname.equals("") || password.equals("") || phone.equals("")) {
            return 0;
        }

        if (isExist(aname)) {
            return 2;
        }

        String salt = generateSalt();
        String encodedPassword = md5Hex(password + salt);
        admin.setSalt(salt);
        admin.setPassword(encodedPassword);
        adminDAO.save(admin);
        return 1;
    }

    public Admin passwordReset(Admin admin) {
        String salt = generateSalt();
        String encodedPassword = md5Hex("rootpassword"+salt);
        admin.setSalt(salt);
        admin.setPassword(encodedPassword);
        return adminDAO.save(admin);
    }

    public Admin passwordChange(String name, String pwd) {
        Admin adminInDB = adminDAO.findByAname(name);
        String salt = generateSalt();
        String encodedPassword = md5Hex(pwd+salt);
        adminInDB.setSalt(salt);
        adminInDB.setPassword(encodedPassword);
        return adminDAO.save(adminInDB);
    }

    public List<Object[]> getAllAdmins() { return adminDAO.findAllExceptsalt(); }
}
