package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
