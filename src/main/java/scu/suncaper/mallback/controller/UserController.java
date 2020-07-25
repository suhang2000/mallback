package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.User;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/login/user")
    @ResponseBody
    public Result userLogin(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String uname = requestUser.getUname();
        uname = HtmlUtils.htmlEscape(uname);
        String password = requestUser.getPassword();

        User user = userService.findByUname(uname);
        if(user == null) {
            return ResultFactory.buildFailResult("用户不存在");
        }
        user = userService.get(uname,password);
        if(user == null) {
            return ResultFactory.buildFailResult("密码不匹配");
        }
        return ResultFactory.buildSuccessResult(uname);
    }

    @CrossOrigin
    @PostMapping("/api/register/user")
    public Result userRegister(@RequestBody User user) {
        int status = userService.userRegister(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("用户名/密码/电话号不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户名重复");
            default:
                return ResultFactory.buildFailResult("未知错误");
        }
    }

    @CrossOrigin
    @PostMapping("/api/pwdreset/user")
    @ResponseBody
    public Result userPwdReset(@RequestBody User requestUser) {
        String uname = HtmlUtils.htmlEscape(requestUser.getUname());
        String phone = HtmlUtils.htmlEscape(requestUser.getPhone());
        String password = requestUser.getPassword();
        User user = userService.findByUname(uname);
        if(user == null) {
            return ResultFactory.buildFailResult("用户不存在");
        }
        user = userService.findByUnameAndPhone(uname,phone);
        if(user == null) {
            return ResultFactory.buildFailResult("号码不匹配");
        }
        user.setPassword(password);
        userService.save(user);
        return ResultFactory.buildSuccessResult(uname);
    }

    @CrossOrigin
    @PostMapping("/api/admin/user")
    public List<Object[]> list() { return userService.getAllUsers(); }

    @CrossOrigin
    @PostMapping("/api/admin/deleuser")
    @Transactional
//    删除操作须得加@Transanctional
//    数据库须得设置为级联删除
    public Result deleUser(@RequestBody User requestUser) {
        Integer uid = requestUser.getUid();
        userService.deleteByUid(uid);
        if (null == userService.findByUid(uid))
            return ResultFactory.buildSuccessResult("成功删除");
        else
            return ResultFactory.buildFailResult("后端出错，删除失败");
    }

    @CrossOrigin
    @GetMapping("/api/home/user/{uname}")
    public User getUser(@PathVariable String uname) {
        return userService.findByUname(uname);
    }

    @CrossOrigin
    @PostMapping("/api/home/user/info")
    public void saveUser(@RequestBody User user) {
        String userUname = user.getUname();
        User newUser = userService.findByUname(userUname);
        user.setPassword(newUser.getPassword());
        user.setUid(newUser.getUid());
        userService.save(user);
    }
}










