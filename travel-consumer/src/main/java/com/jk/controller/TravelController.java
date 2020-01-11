package com.jk.controller;

import com.jk.model.UserModel;
import com.jk.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("wh")
public class TravelController {

    @Autowired
    private TravelService travelService;

    //到登录页面
    @RequestMapping("dl")
    public String dl(){
        return "login";
    }
    //登录验证
    @GetMapping("denglu")
    @ResponseBody
    public HashMap<String,Object> login(@RequestParam("userphone") String userphone,@RequestParam("userword") String userword, HttpServletRequest request){
        HashMap<String,Object> hm = new HashMap<String,Object>();
        UserModel loginUserModel = travelService.queryUserphone(userphone);
        //存id
        //request.getSession().setAttribute("user", loginUser);
        if(loginUserModel ==null){
            hm.put("code",1);
            hm.put("msg","手机号不存在");
            System.out.println("用户名不存在");
        }else{
            if(!loginUserModel.getPassword().equals(userword)){
                hm.put("code",2);
                hm.put("msg","密码不正确");
                System.out.println("密码不正确");
            }else{
                // redisTemplate.opsForValue().set("user_txd",loginUser.getUserId());
                request.getSession().setAttribute(request.getSession().getId(), loginUserModel);
                hm.put("code",3);
                hm.put("msg","登陆成功");
                System.out.println("登陆成功");
            }
        }
        return hm;
    }
    //进来首页
    @RequestMapping("toindex")
    public String index(){
        return "index";
    }
    //去注册页面
    @RequestMapping("tozc")
    public String zc(){
        return "register";
    }
    //获取验证码
    @RequestMapping("yanzheng")
    @ResponseBody
    public Map yanzheng(@RequestParam("userphone") String userphone){
        System.out.println("这是注册短信发送----------------------------");
        System.out.println(userphone+"----------------------------------------");
        return travelService.yanzheng(userphone);
    }
    //判断用户+注册
    @PutMapping("register")
    @ResponseBody
    public String register(UserModel yh){
        return travelService.register(yh);
    }


}
