package com.hrj.service;

import com.hrj.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    boolean setCode(String userAccountNum);
    String getCode(String userAccountNum);
    /**
     * 通过手机号查找用户是否存在
     * @param userAccountNum
     * @return
     */
    User selectByUserAccountNum(String userAccountNum);

    /**
     * 插入用户
     * @param record
     * @return
     */
    int insert(User record);
}
