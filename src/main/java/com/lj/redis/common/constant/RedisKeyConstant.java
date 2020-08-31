package com.lj.redis.common.constant;

/**
 * @Description: redis  key值常量类
 * @ClassName: RedisKeyConstant
 * @Author: liang_jun
 * @Date: 2020/8/23 17:22
 */
public interface RedisKeyConstant {

    /**
     * 1) 第一段放置项目名或缩写 如 project
     * <p>
     * 1) 第二段把表名转换为key前缀 如, user:
     * <p>
     * 2) 第三段放置用于区分区key的字段,对应mysql中的主键的列名,如userid
     * <p>
     * 3) 第四段放置主键值,如18,16
     */

    /**
     * redis 存储用户name key值
     */
    String REDIS_USER_NAME = "REDIS:USER:NAME:";

    /**
     * redis 存储用户phone key值
     */
    String USER_USER_PHONE = "REDIS:USER:PHONE:";

    /**
     * redis 存储用户id key值
     */
    String REDIS_USER_ID = "REDIS:USER:ID:";
}
