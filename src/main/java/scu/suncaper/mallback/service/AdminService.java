package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.dao.AdminDAO;
import scu.suncaper.mallback.pojo.Admin;

import java.security.SecureRandom;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    public Admin findByAname(String aname) {
        return adminDAO.findByAname(aname);
    }

    public Admin get(String aname, String password) {
        return adminDAO.getByAnameAndPassword(aname, password);
    }

    public boolean isExist(int aid) {
        Admin admin = adminDAO.findByAid(aid);
        return null != admin;
    }
    // 生成 20 位salt
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        String salt = encodeBase64String(bytes);
        return salt;
    }

    public int adminRegister(Admin admin) {
//        可能需要添加防止输入字符串包括其他字符的验证
        int aid = admin.getAid();
        String password = admin.getPassword();
        String aname = admin.getAname();
        String phone = admin.getPhone();

        admin.setAid(aid);
        password = HtmlUtils.htmlEscape(password);
        admin.setPassword(password);
        aname = HtmlUtils.htmlEscape(aname);
        admin.setAname(aname);
        phone = HtmlUtils.htmlEscape(phone);
        admin.setPhone(phone);

        if (aname.equals("") || password.equals("") || phone.equals("")) {
            return 0;
        }

        if (isExist(aid)) {
            return 2;
        }

        String salt = generateSalt();
        String encodedPassword = md5Hex(password+salt);
        admin.setSalt(salt);
        admin.setPassword(encodedPassword);
        adminDAO.save(admin);
        return 1;
    }

    public void adminEdit(Admin admin) {
        Admin adminInDB = adminDAO.findByAname(admin.getAname());
        adminInDB.setAname(admin.getAname());
        adminInDB.setPhone(admin.getPhone());
        adminDAO.save(adminInDB);
    }

    public Admin passwordReset(Admin admin) {
        Admin adminInDB = adminDAO.findByAname(admin.getAname());
        String salt = generateSalt();
        String encodedPassword = md5Hex("rootpassword"+salt);
        admin.setSalt(salt);
        admin.setPassword(encodedPassword);
        return adminDAO.save(adminInDB);
    }
}
