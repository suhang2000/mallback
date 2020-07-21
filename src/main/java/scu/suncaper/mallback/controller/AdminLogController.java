package scu.suncaper.mallback.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(aname, requestAdmin.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try{
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(aname);
        }catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("密码不匹配");
        } catch (UnknownAccountException e) {
            return ResultFactory.buildFailResult("用户不存在");
        }
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
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("成功退出");
    }
}
