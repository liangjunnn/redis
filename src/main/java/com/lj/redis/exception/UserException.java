package com.lj.redis.exception;


import com.lj.redis.common.enums.ErrorMsgEnum;

/**
 * @Description: 用户异常
 * @ClassName: UserException
 * @Author: liang_jun
 * @Date: 2020/7/23 15:03
 */
public class UserException extends CustomException {

    public UserException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum);
    }

    public UserException(ErrorMsgEnum errorMsgEnum, Object... args) {
        super(errorMsgEnum, args);
    }

    public UserException(ErrorMsgEnum errorMsgEnum, Throwable t) {
        super(errorMsgEnum, t);
    }
}
