package com.hrj.utils;

import com.hrj.pojo.User;

public class JsonUtils {
    public String status;  // 返回状态

    public String msg;    // 返回的信息


    //    public User user;
    public Object data; // 返回的数据


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
