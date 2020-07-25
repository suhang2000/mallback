package scu.suncaper.mallback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import scu.suncaper.mallback.pojo.Saler;
import scu.suncaper.mallback.result.Result;
import scu.suncaper.mallback.result.ResultFactory;
import scu.suncaper.mallback.service.SalerService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class SalerController {
    @Autowired
    SalerService salerService;

    @CrossOrigin
    @PostMapping("/api/login/saler")
    @ResponseBody
    public Result salerLogin(@RequestBody Saler requestSaler) {
        String sname = requestSaler.getSname();
        sname = HtmlUtils.htmlEscape(sname);
        String password = requestSaler.getPassword();
        Saler saler = salerService.findBySname(sname);
        if(saler == null) {
            return ResultFactory.buildFailResult("商家不存在");
        }
        saler = salerService.get(sname, password);
        if(saler == null) {
            return ResultFactory.buildFailResult("密码不匹配");
        }
        return ResultFactory.buildSuccessResult(sname);
    }

    @CrossOrigin
    @PostMapping("/api/register/saler")
    public Result salerRegister(@RequestBody Saler saler) {
        int status = salerService.salerRegister(saler);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("商家名/密码/电话号不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("商家已存在，请换一个昵称");
            default:
                return ResultFactory.buildFailResult("未知错误");
        }
    }

    @CrossOrigin
    @PostMapping("/api/pwdreset/saler")
    @ResponseBody
    public Result salerPwdReset(@RequestBody Saler requestSaler) {
        String sname = HtmlUtils.htmlEscape(requestSaler.getSname());
        String phone = HtmlUtils.htmlEscape(requestSaler.getPhone());
        String password = requestSaler.getPassword();
        Saler saler = salerService.findBySname(sname);
        if(saler == null) {
            return ResultFactory.buildFailResult("商家不存在");
        }
        saler = salerService.findBySnameAndPhone(sname,phone);
        if(saler == null) {
            return ResultFactory.buildFailResult("号码不匹配");
        }
        saler.setPassword(password);
        salerService.save(saler);
        return ResultFactory.buildSuccessResult(sname);
    }

    @CrossOrigin
    @PostMapping("/api/admin/saler")
    public List<Object[]> list() { return salerService.getAllSalers(); }

    @CrossOrigin
    @PostMapping("/api/admin/delesaler")
    @Transactional
    public Result deleUser(@RequestBody Saler requestSaler) {
        Integer sid = requestSaler.getSid();
        salerService.deleteBySid(sid);
        if (null == salerService.findBySid(sid)) {
            return ResultFactory.buildSuccessResult("成功删除");
        } else {
            return ResultFactory.buildFailResult("服务端出错，删除失败");
        }
    }

    @CrossOrigin
    @GetMapping("/api/saler/{sname}")
    public int getSidBySname(@PathVariable String sname) {
        Saler saler = salerService.findBySname(sname);
        return saler.getSid();
    }

    @CrossOrigin
    @GetMapping("/api/saler/{sname}/info")
    public Saler salerInfo(@PathVariable String sname) {
        return salerService.findBySname(sname);
    }

    @CrossOrigin
    @PostMapping("api/saler/info")
    public void saveSaler(@RequestBody Saler saler) {
        Saler newSaler = salerService.findBySname(saler.getSname());
        newSaler.setPhone(saler.getPhone());
        newSaler.setEmail(saler.getEmail());
        newSaler.setAddress(saler.getAddress());
        newSaler.setBank_num(saler.getBank_num());
        salerService.save(newSaler);
    }
}
