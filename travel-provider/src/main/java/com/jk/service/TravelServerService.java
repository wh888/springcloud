package com.jk.service;

import com.jk.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface TravelServerService {
    UserModel queryUserphone(String userphone);

    Map yanzheng(String userphone);

    String register(UserModel yh);
}
