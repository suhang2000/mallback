package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.dao.SalerDAO;
import scu.suncaper.mallback.pojo.Saler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SalerService {
    @Autowired
    SalerDAO salerDAO;

    public Saler findBySname(String sname) { return salerDAO.findBySname(sname);}

    public Saler get(String sname, String password) { return salerDAO.getBySnameAndPassword(sname,password);}

    public Saler findBySnameAndPhone(String sname, String phone) { return salerDAO.findBySnameAndPhone(sname,phone);}

    public void save(Saler saler) { salerDAO.save(saler);}

    public boolean isExist(String sname) {
        Saler saler = salerDAO.findBySname(sname);
        return null != saler;
    }

    public int salerRegister(Saler saler) {
        String sname = saler.getSname();
        sname = HtmlUtils.htmlEscape(sname);
        saler.setSname(sname);
        if (isExist(sname)) { return 2; }
        String password = saler.getPassword();
        String phone = saler.getPhone();
        String email = saler.getEmail();
        String address = saler.getAddress();
        String bank_num = saler.getBank_num();

        password = HtmlUtils.htmlEscape(password);
        saler.setPassword(password);
        phone = HtmlUtils.htmlEscape(phone);
        saler.setPhone(phone);
        saler.setEmail(HtmlUtils.htmlEscape(email));
        saler.setAddress(HtmlUtils.htmlEscape(address));
        saler.setBank_num(HtmlUtils.htmlEscape(bank_num));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            saler.setRegister_time(sdf.parse(sdf.format(new Date())));
        }catch (ParseException e){
            e.printStackTrace();
        }
        if (sname.equals("") || password.equals("") || phone.equals("")) {
            return 0;
        }
        salerDAO.save(saler);
        return 1;
    }
}
