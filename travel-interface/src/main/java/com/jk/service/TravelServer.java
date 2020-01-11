package com.jk.service;

import com.jk.model.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface  TravelServer {

    @GetMapping("query")
    public UserModel queryUserphone(@RequestParam("userphone") String userphone);

    @GetMapping("yanzheng")
    public Map yanzheng(@RequestParam("userphone") String userphone);

    @PutMapping("xz")
    public String register(@RequestBody UserModel yh);

}
