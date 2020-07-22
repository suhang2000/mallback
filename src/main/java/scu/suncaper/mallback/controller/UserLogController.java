package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.User;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.UserService;

import java.util.Objects;

@RestController
public class UserLogController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/login/user")
    @ResponseBody
    public Result list(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        System.out.println(requestUser);
        String uname = requestUser.getUname();
        uname = HtmlUtils.htmlEscape(uname);
        String password = requestUser.getPassword();

        User user = userService.findByUname(uname);
        if(user == null)
            return ResultFactory.buildFailResult("用户不存在");
        user = userService.get(uname,password);
        if(user == null)
            return ResultFactory.buildFailResult("密码不匹配");
        return ResultFactory.buildSuccessResult(uname);
    }
}










