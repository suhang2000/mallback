package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Admin;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.service.AdminService;

@RestController
public class AdminLoginController {
    @Autowired
    AdminService adminService;

    @CrossOrigin
    @PostMapping("/api/login/admin")
    @ResponseBody
    public Result AdminLogin(@RequestBody Admin requestAdmin) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String aname = requestAdmin.getAname();
        aname = HtmlUtils.htmlEscape(aname);
        System.out.println(aname);
        String password = requestAdmin.getPassword();

        Admin admin = adminService.get(aname, password);
        System.out.println(admin);
        if(admin == null) {
            return new Result(400);
        }else {
            return new Result(200);
        }
    }
}
