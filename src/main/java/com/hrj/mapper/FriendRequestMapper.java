package com.hrj.mapper;

import com.hrj.pojo.FriendRequest;

public interface FriendRequestMapper {
    int deleteByPrimaryKey(String id);

    int insert(FriendRequest record);

    int insertSelective(FriendRequest record);

    FriendRequest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FriendRequest record);

    int updateByPrimaryKey(FriendRequest record);
}