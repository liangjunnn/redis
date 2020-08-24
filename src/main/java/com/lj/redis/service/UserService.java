package com.lj.redis.service;

import com.alibaba.fastjson.JSON;
import com.lj.redis.common.Result;
import com.lj.redis.common.enums.ErrorMsgEnum;
import com.lj.redis.common.util.RedisUtil;
import com.lj.redis.exception.UserException;
import com.lj.redis.mapper.UserMapper;
import com.lj.redis.pojo.response.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 用户服务
 * @ClassName: UserService
 * @Author: liang_jun
 * @Date: 2020/8/21 12:05
 */

public interface UserService {

    Object getUserById(Long id);

    int upUserById(Long id, String name);
}
