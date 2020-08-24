package com.lj.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.lj.redis.common.enums.ErrorMsgEnum;
import com.lj.redis.common.util.RedisUtil;
import com.lj.redis.exception.UserException;
import com.lj.redis.mapper.UserMapper;
import com.lj.redis.pojo.response.UserVO;
import com.lj.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 用户服务
 * @ClassName: UserServiceImpl
 * @Author: liang_jun
 * @Date: 2020/8/22 11:46
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private RedisUtil redisUtil;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RedisUtil redisUtil) {
        this.userMapper = userMapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public Object getUserById(Long id) {
        //访问用户先从redis读取是否有数据,没有从db拿,再放入redis;
        Object userObj = redisUtil.get(String.valueOf(id));
        UserVO user = null;
        if (null == userObj) {
            //如果db没有数据，直接返回异常;
            user = userMapper.selectUserById(id);
            if (null == user) {
                throw new UserException(ErrorMsgEnum.USER_THERE_IS_NO);
            }
            //db数据存在 写入redis
            redisUtil.set(String.valueOf(id), user);
            log.info("根据用户唯一编号获取用户信息db返回 user:{}", JSON.toJSONString(user));
            return user;
        }
        log.info("根据用户唯一编号获取用户信息redis返回 userObj:{}", JSON.toJSONString(userObj));
        return userObj;
    }

    @Override
    public int upUserById(Long id, String name) {
        int line = userMapper.upUserById(id, name);
        if (line < 1) {
            throw new UserException(ErrorMsgEnum.USER_UPDATE_ERROR);
        }
        redisUtil.del(String.valueOf(id));
        return line;
    }
}
