package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.jk.mapper.TravelServerMapper;
import com.jk.model.UserModel;
import com.jk.util.CheckSumBuilder;
import com.jk.util.CommonConf;
import com.jk.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class TravelServerServiceImpl implements TravelServerService{

    @Autowired
    private TravelServerMapper travelServerMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public UserModel queryUserphone(String userphone) {

        return travelServerMapper.queryUserphone(userphone);
    }

    @Override
    public Map yanzheng(String userphone) {
        //构建总返回
        HashMap<Object, Object> result = new HashMap<>();
        try {
            //判断当前请求距离上一次请求是否超过1分钟
            //一分钟内不能重复获取短信锁
            String lockCacheKey = CommonConf.SSM_CODE_LOCK+userphone;
            if (redisTemplate.hasKey(lockCacheKey)) {
                result.put("code",2);
                result.put("msg","您操作太过频繁,请稍后再试");
                return result;
            }
            //请求头部信息
            HashMap<String, Object> headers = new HashMap<>();
            headers.put("AppKey", CommonConf.WANG_YI_APP_KEY);
            String nonce = UUID.randomUUID().toString().replaceAll("-", "");
            headers.put("Nonce", nonce);
            String curTime = System.currentTimeMillis()/1000+"";
            headers.put("CurTime", curTime);
            String checkSum = CheckSumBuilder.getCheckSum(CommonConf.WANG_YI_APP_SECRET, nonce, curTime);
            headers.put("CheckSum", checkSum);

            //请求参数信息
            HashMap<String, Object> params= new HashMap<>();
            params.put("mobile", userphone);
            params.put("templateid", CommonConf.WANG_YI_TEMPLATEID);
            //随机生成6个数字作为验证码
            int authCode = (int)((Math.random()*9+1)*100000);
            System.out.println("验证码："+authCode);
            params.put("authCode", String.valueOf(authCode));
            String post = HttpClientUtil.post(CommonConf.WANG_YI_SMS, params, headers);
            JSONObject parseObject = JSON.parseObject(post);
            int code = parseObject.getIntValue("code");
            if(code!=200){
                result.put("code", 2);
                result.put("msg", "验证码发送失败");
                return result;
            }else{
                //发送成功后经验证码保存到redis中
                redisTemplate.opsForValue().set(CommonConf.SSM_CODE_CACHE+userphone,String.valueOf(authCode), 10, TimeUnit.MINUTES);
                //将用户枷锁一分钟
                redisTemplate.opsForValue().set(lockCacheKey,"lock",1, TimeUnit.MINUTES);
                result.put("code", 4);
                result.put("msg", "发送成功");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 5);
            result.put("msg", "验证码发送失败");
            return result;
        }
    }

    @Override
    public String register(UserModel yh) {
        UserModel mm = travelServerMapper.queryName(yh.getUsername());
        if (mm!=null){
            return "用户已存在";
        }
        //验证码
        String key = CommonConf.SSM_CODE_CACHE+yh.getUsersj();
        Boolean flag = redisTemplate.hasKey(key);
        if (!flag){
            return  "请重新获取验证码";
        }
        String checkCode=yh.getYzm();
        //判断验证码是否正确
        String redisCode=redisTemplate.opsForValue().get(key).toString();
        if (!redisCode.equals(checkCode)){
            return  "验证码错误！";
        }
        Long yhId=travelServerMapper.save(yh);
        if (yhId==null){
            return "网络错误,请稍后再试";
        }
        return "注册成功";
    }
}
