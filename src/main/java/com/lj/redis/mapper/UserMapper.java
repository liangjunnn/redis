package com.lj.redis.mapper;

import com.lj.redis.pojo.response.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @ClassName: UserMapper
 * @Author: liang_jun
 * @Date: 2020/8/21 12:06
 */
public interface UserMapper {

    UserVO selectUserById(@Param("id") Long id);

    int upUserById(@Param("id") Long id,@Param("name") String name);
}
