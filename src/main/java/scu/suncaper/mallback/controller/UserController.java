package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import scu.suncaper.mallback.pojo.User;
import scu.suncaper.mallback.service.UserService;

/**
 * @author 李苏航
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/home/user/{uname}")
    public User getUser(@PathVariable String uname) {
        System.out.println(uname);
        return userService.findByUname(uname);
    }
}
