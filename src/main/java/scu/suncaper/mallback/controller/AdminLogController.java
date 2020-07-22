package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Admin;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.AdminService;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@RestController
public class AdminLogController {
    @Autowired
    AdminService adminService;

    @CrossOrigin
//    PostMapping应与前端对应
    @PostMapping("/api/login/admin")
    @ResponseBody
    public Result AdminLogin(@RequestBody Admin requestAdmin) {
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
        if(admin == null)
            return ResultFactory.buildFailResult("密码不匹配");
        return ResultFactory.buildSuccessResult(aname);
    }

//    前端点击事件无响应多半是未跨域！
    @CrossOrigin
    @PostMapping("/api/register/admin")
    public Result AdminRegister(@RequestBody Admin admin) {
        int status = adminService.adminRegister(admin);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("姓名/密码/电话号码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("管理人员重复注册");
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
