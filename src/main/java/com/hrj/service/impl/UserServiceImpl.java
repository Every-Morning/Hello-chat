package com.hrj.service.impl;

import com.hrj.mapper.UserMapper;
import com.hrj.pojo.User;
import com.hrj.service.UserService;
import com.hrj.utils.MobilePhoneVerificationCode;
import com.hrj.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RedisUtils redisUtils;

    @Autowired(required = false)
    UserMapper userMapper;
    @Override
    public boolean setCode(String userAccountNum) {
        String msg=MobilePhoneVerificationCode.getPhonemsg(userAccountNum); // 发送验证码
        if(msg=="true") {
            redisUtils.setRedis(userAccountNum, MobilePhoneVerificationCode.code, 60);
        }else {
            return false;
        }
        return true;
    }

    @Override
    public String getCode(String userAccountNum) {
        return redisUtils.getRedis(userAccountNum);
    }

    @Override
    public User selectByUserAccountNum(String userAccountNum) {
        return userMapper.selectByUserAccountNum(userAccountNum);
    }

    /**
     * 插入用户
     *
     * @param record
     * @return
     */
    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }
}
