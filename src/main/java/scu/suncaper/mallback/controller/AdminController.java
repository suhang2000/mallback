package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Admin;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.AdminService;

import java.util.List;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @CrossOrigin
//    改变PostMapping会导致登录报错"服务器异常"
    @PostMapping("/api/login/admin")
    @ResponseBody
    public Result adminLogin(@RequestBody Admin requestAdmin) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String aname = requestAdmin.getAname();
        aname = HtmlUtils.htmlEscape(aname);
        String password = requestAdmin.getPassword();
        Admin admin = adminService.findByAname(aname);
        if(admin == null) {
            return ResultFactory.buildFailResult("管理账号不存在");
        }
        password = md5Hex(password+admin.getSalt());
        admin = adminService.get(aname, password);
        if(admin == null) {
            return ResultFactory.buildFailResult("密码不匹配");
        }
        return ResultFactory.buildSuccessResult(aname);
    }

    //    前端点击事件无响应多半是未跨域！
    @CrossOrigin
    @PostMapping("/api/register/admin")
    public Result adminRegister(@RequestBody Admin admin) {
        int status = adminService.adminRegister(admin);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("姓名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("成功注册");
            case 2:
                return ResultFactory.buildFailResult("管理人员重复注册");
            default:
                return ResultFactory.buildFailResult("未知错误");
        }
    }

    @CrossOrigin
    @PostMapping("/api/admin/admininfo")
    public List<Object[]> list() { return adminService.getAllAdmins(); }

    @CrossOrigin
    @PostMapping("/api/admin/pwdreset")
    public Result adminPwdReset(@RequestBody Admin admin) {
        int aid = admin.getAid();
        admin = adminService.findByAid(aid);
        admin = adminService.passwordReset(admin);
        if (admin != null)
            return ResultFactory.buildSuccessResult("成功重置");
        else
            return ResultFactory.buildSuccessResult("未知错误");
    }

    @CrossOrigin
    @PostMapping("/api/pwdreset/admin")
    @ResponseBody
    public Result AdminPwdChange(@RequestBody Admin requestAdmin) {
        String aname = HtmlUtils.htmlEscape(requestAdmin.getAname());
        String password = requestAdmin.getPassword();
        requestAdmin = adminService.passwordChange(aname,password);
        if (requestAdmin != null)
            return ResultFactory.buildSuccessResult("成功修改");
        else
            return ResultFactory.buildSuccessResult("未知错误");
    }


}
