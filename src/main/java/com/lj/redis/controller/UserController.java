package com.lj.redis.controller;

import com.alibaba.fastjson.JSON;
import com.lj.redis.common.Result;
import com.lj.redis.common.enums.ErrorMsgEnum;
import com.lj.redis.common.util.RedisUtil;
import com.lj.redis.exception.UserException;
import com.lj.redis.pojo.response.UserVO;
import com.lj.redis.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户控制器
 * @ClassName: UserController
 * @Author: liang_jun
 * @Date: 2020/8/21 12:04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * redis服务
     */
    private final RedisUtil redisUtil;


    @Autowired
    private UserController(UserService userService, RedisUtil redisUtil) {
        this.userService = userService;
        this.redisUtil = redisUtil;
    }

    @GetMapping("/queryUserAllById")
    public Result<Object> queryUserAllById(Long id) {
        log.info("根据用户唯一编号获取用户信息请参 id:{}", id);
        if (null == id) {
            return new Result<Object>(ErrorMsgEnum.PARAMETER_EXCEPTION.getCode(), ErrorMsgEnum.PARAMETER_EXCEPTION.getMsg());
        }
        Object user = userService.getUserById(id);
        return new Result<Object>(user);

    }

    /**
     *  
     *
     * @author liang_jun
     * @param id : 
     * @param name :  
     * @return 
     * @throws 
     * @date 2020/8/22 11:34
     */	
    @GetMapping("/modifyUserById")
    public Result<Object> modifyUserById(Long id, String name) {
        if (null == id || StringUtils.isBlank(name)) {
            return new Result<Object>(ErrorMsgEnum.PARAMETER_EXCEPTION.getCode(), ErrorMsgEnum.PARAMETER_EXCEPTION.getMsg());
        }
        try {
            userService.upUserById(id, name);
        } catch (UserException e) {
            return new Result<Object>(e.getResultStatusEnum().getCode(), e.getResultStatusEnum().getMsg());
        }
        return new Result<Object>();
    }


}
