package scu.suncaper.mallback.controller;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Admin;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.AdminService;

@RestController
public class AdminLogController {
    @Autowired
    AdminService adminService;

    @CrossOrigin
//    改变PostMapping会导致登录报错"服务器异常"
    @PostMapping("/api/admin/login")
    @ResponseBody
    public Result AdminLogin(@RequestBody Admin requestAdmin) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String aname = requestAdmin.getAname();
        aname = HtmlUtils.htmlEscape(aname);
        String password = requestAdmin.getPassword();
        Admin admin = adminService.findByAname(aname);
        if(admin == null)
            return ResultFactory.buildFailResult("用户不存在");
        password = new SimpleHash("md5", password,admin.getSalt() , 2).toString();
        admin = adminService.get(aname, password);
        if(admin == null)
            return ResultFactory.buildFailResult("密码不匹配");
        else
            System.out.println("has been invoked");
            return ResultFactory.buildSuccessResult(aname);
    }

//    前端点击事件无响应多半是未跨域！
    @CrossOrigin
    @PostMapping("/api/admin/register")
    public Result AdminRegister(@RequestBody Admin admin) {
        int status = adminService.adminRegister(admin);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("姓名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户重复注册");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @CrossOrigin
    @GetMapping("/api/admin/logout")
    public Result logout() {
//       TODO
        return ResultFactory.buildSuccessResult("成功退出");
    }
}
