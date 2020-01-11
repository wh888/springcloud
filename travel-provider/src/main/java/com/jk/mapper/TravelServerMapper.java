package com.jk.mapper;

import com.jk.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


@Repository
public interface TravelServerMapper {
    @Select("SELECT * FROM ly_user WHERE usersj =#{value}")
    UserModel queryUserphone(String userphone);
    @Select("SELECT * FROM  ly_user where userName=#{value}")
    UserModel queryName(String username);

    Long save(UserModel yh);
}
