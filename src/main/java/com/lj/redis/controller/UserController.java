package com.lj.redis.controller;

import com.lj.redis.common.Result;
import com.lj.redis.common.constant.RedisKeyConstant;
import com.lj.redis.common.enums.ErrorMsgEnum;
import com.lj.redis.common.util.RedisUtil;
import com.lj.redis.exception.UserException;
import com.lj.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private RedisUtil redisUtil;


    @Autowired
    private UserController(UserService userService, RedisUtil redisUtil) {
        this.userService = userService;
        this.redisUtil = redisUtil;
    }

    /**
     * 唯一编号查询用户详情
     *
     * @param id :  用户id
     * @return 返回用户详情
     * @author liang_jun
     * @date 2020/8/22 11:59
     */
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
     * 修改用户昵称
     *
     * @param id   : 用户唯一编号
     * @param name :  用户昵称
     * @return 返回 影响行数
     * @author liang_jun
     * @date 2020/8/22 11:45
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


    /**
     * 将数据放入set
     *
     * @param key :
     * @return
     * @throws
     * @author liang_jun
     * @date 2020/8/24 10:59
     */
    @GetMapping("/set")
    public Result<Object> Sset(String key) {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        redisUtil.sSet(key, integers);
        return new Result<Object>();
    }

    /**
     * 获取set缓存内容
     *
     * @param key :
     * @return
     * @throws
     * @author liang_jun
     * @date 2020/8/24 10:59
     */
    @GetMapping("/Gset")
    public Result<Object> Gset(String key) {
        Set<Object> objects = redisUtil.sGet(key);
        return new Result<>(objects);
    }

    /**
     * 将数据放入list  缓存
     *
     * @return
     * @throws
     * @author liang_jun
     * @date 2020/8/24 11:00
     */
    @GetMapping("/lSet")
    public Result<Object> lSet() {
        List<String> names = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        names.add("猪头");
        names.add("肥猪");
        names.add("死肥猪");

        phones.add("1111");
        phones.add("2222");
        phones.add("3333");
        redisUtil.lSet(RedisKeyConstant.REDIS_USER_NAME, names);
        redisUtil.lSet(RedisKeyConstant.USER_USER_PHONE, phones);
        return new Result<>();
    }

    /**
     * 通过索引获取list缓存内容
     *
     * @param key   :
     * @param index :
     * @return
     * @throws
     * @author liang_jun
     * @date 2020/8/24 11:01
     */
    @GetMapping("/lGetIndex")
    public Result<Object> lGetIndex(String key, long index) {
        Object o = redisUtil.lGetIndex(key, index);
        return new Result<Object>(o);
    }


}
