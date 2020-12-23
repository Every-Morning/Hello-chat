package com.hrj.mapper;

import com.hrj.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    /**
     * 通过手机号查找用户是否存在
     * @param userAccountNum
     * @return
     */
    User selectByUserAccountNum(@Param("userAccountNum") String userAccountNum);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}