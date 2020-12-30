package com.hrj.controller;

import com.alibaba.fastjson.JSON;
import com.hrj.constant.ResponseJson;
import com.hrj.pojo.User;
import com.hrj.service.impl.UserServiceImpl;
import com.hrj.utils.JsonUtils;
import com.hrj.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    UserServiceImpl userService;

    /**
     * 发送短信验证码
     * @param userAccountNum
     * @return
     */
    @PostMapping("/setCode")
    public String setCode(String userAccountNum){
        log.info(userAccountNum);
        JsonUtils jsonUtils = new JsonUtils(); // 将返回信息封装

//        if(userService.selectByUserAccountNum(userAccountNum)!=null){
//            jsonUtils.setStatus(ResponseJson.Exist_ERROR_STATUS);
//            jsonUtils.setMsg("该用户已经存在，无需注册");
//
//        }else{
                userService.setCode(userAccountNum);
                jsonUtils.setStatus(ResponseJson.SUCCESS_STATUS);
//        }
        log.info(JSON.toJSONString(jsonUtils));
        return  JSON.toJSONString(jsonUtils);

    }


    /**
     * 注册并登录
     * @param userAccountNum
     * @param code
     * @return
     */
    @PostMapping("/reg_login")
    public JsonUtils regLogin(String userAccountNum,String code){
        JsonUtils jsonUtils = new JsonUtils(); // 将返回信息封装
        String v=userService.getCode(userAccountNum);
        User user=userService.selectByUserAccountNum(userAccountNum);
        // 判断用户输入的验证码是否与redis缓存中的验证码是否一致
        if(code.equals(v)){
            // 验证数据库中是否已经存在该用户,存在直接登录
            if(user!=null){

            jsonUtils.setStatus(ResponseJson.SUCCESS_STATUS);
            jsonUtils.setMsg("登入成功");

            }// 用户不存在自动注册
            else {
                user.setUserId(userAccountNum);
                // 以当前时间为默认用户名
                String userName=String.valueOf(System.currentTimeMillis());
                user.setUserName(userName);
                user.setUserAccountNum(userAccountNum);
                user.setUserQrcode(""); // 二维码
                user.setUserImage(""); // 小头像
                user.setUserImageBig(""); // 头像
                int i = userService.insert(user);
                log.info("数据库插入返回值：" + i);
                jsonUtils.setStatus(ResponseJson.SUCCESS_STATUS);
                jsonUtils.setMsg("注册成功");

            }
        }else{
            jsonUtils.setStatus(ResponseJson.ERROR_STATUS);
            jsonUtils.setMsg("验证码无效");
        }
        UserVo userVo= new UserVo();
        BeanUtils.copyProperties(user,userVo);
        jsonUtils.setData(userVo);
        log.info(v);
        log.info(JSON.toJSONString(jsonUtils));
        return jsonUtils;
    }

    @PostMapping("/login")
    public JsonUtils login(String userAccountNum,String userPassword){
        JsonUtils jsonUtils= new JsonUtils();
        UserVo userVo= new UserVo();
        // 查询该用户的信息
        User user=userService.selectByUserAccountNum(userAccountNum);
        if(user!=null){
            if(userPassword.equals(user.getUserPassword())){
                BeanUtils.copyProperties(user,userVo);
                jsonUtils.setData(userVo);
               jsonUtils.setStatus(ResponseJson.SUCCESS_STATUS);
               jsonUtils.setMsg("登入成功");
            } else if(user.getUserPassword()==null){
                jsonUtils.setStatus(ResponseJson.ERROR_STATUS);
                jsonUtils.setMsg("您的账号，修改密码，请选择短信登录");
            }
            else {
                jsonUtils.setStatus(ResponseJson.ERROR_STATUS);
                jsonUtils.setMsg("密码错误");
            }
        }
        else {
            jsonUtils.setStatus(ResponseJson.ERROR_STATUS);
            jsonUtils.setMsg("该用户不存在");
        }
        log.info(JSON.toJSONString(jsonUtils));
        return jsonUtils;
    }


}
