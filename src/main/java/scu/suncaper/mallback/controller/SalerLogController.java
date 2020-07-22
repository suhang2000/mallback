package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Saler;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.SalerService;

@RestController
public class SalerLogController {
    @Autowired
    SalerService salerService;

    @CrossOrigin
    @PostMapping("/api/login/saler")
    @ResponseBody
    public Result SalerLogin(@RequestBody Saler requestSaler) {
        String sname = requestSaler.getSname();
        sname = HtmlUtils.htmlEscape(sname);
        String password = requestSaler.getPassword();
        Saler saler = salerService.findBySname(sname);
        if(saler == null) {
            return ResultFactory.buildFailResult("商家不存在");
        }
        saler = salerService.get(sname, password);
        if(saler == null)
            return ResultFactory.buildFailResult("密码不匹配");
        return ResultFactory.buildSuccessResult(sname);
    }

    @CrossOrigin
    @PostMapping("/api/register/saler")
    public Result SalerRegister(@RequestBody Saler saler) {
        int status = salerService.salerRegister(saler);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("昵称和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("昵称重复");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @CrossOrigin
    @GetMapping("/api/saler/logout")
    public Result logout() {
//       TODO
        return ResultFactory.buildSuccessResult("成功退出");
    }
}
