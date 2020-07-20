package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.suncaper.mallback.dao.AdminDAO;
import scu.suncaper.mallback.pojo.Admin;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    public Admin get(String aname, String password) {
        return adminDAO.getByAnameAndPassword(aname, password);
    }
}
