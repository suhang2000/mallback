package scu.suncaper.mallback.service;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.dao.AdminDAO;
import scu.suncaper.mallback.pojo.Admin;

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

        // 生成 24 位salt
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        // 生成 32 位加密密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
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
//        adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
    }

    public Admin passwordReset(Admin admin) {
        Admin adminInDB = adminDAO.findByAname(admin.getAname());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;

        adminInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "rootpassword", salt, times).toString();
        adminInDB.setPassword(encodedPassword);
        return adminDAO.save(adminInDB);
    }
}
