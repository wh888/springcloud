package com.jk.controller;

import com.jk.model.UserModel;
import com.jk.service.TravelServer;
import com.jk.service.TravelServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class TravelServerController implements TravelServer {

    @Autowired
    private TravelServerService travelServerService;


    @GetMapping("query")
    @ResponseBody
    public UserModel queryUserphone(String userphone) {

        return travelServerService.queryUserphone(userphone);
    }

    @GetMapping("yanzheng")
    @ResponseBody
    public Map yanzheng(String userphone) {
       return travelServerService.yanzheng(userphone);
        }

    @PutMapping("xz")
    @ResponseBody
    public String register(UserModel yh) {
        return travelServerService.register(yh);
    }

}
