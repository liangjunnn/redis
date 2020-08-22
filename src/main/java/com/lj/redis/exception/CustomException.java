package com.lj.redis.exception;


import com.lj.redis.common.enums.ErrorMsgEnum;

/**
 * @Description: 自定义异常
 * @ClassName: CustomException
 * @Author: liang_jun
 * @Date: 2020/7/23 10:35
 */
public class CustomException extends RuntimeException {

    private ErrorMsgEnum errorMsgEnum;

    public CustomException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum.getMsg());
        this.errorMsgEnum = errorMsgEnum;
    }

    public CustomException(ErrorMsgEnum errorMsgEnum, Object... args) {
        super(errorMsgEnum.getMsg(errorMsgEnum, args));
        this.errorMsgEnum = errorMsgEnum;
    }

    public CustomException(ErrorMsgEnum errorMsgEnum, Throwable t) {
        super(errorMsgEnum.getMsg(), t);
        this.errorMsgEnum = errorMsgEnum;
    }

    public ErrorMsgEnum getResultStatusEnum() {
        return errorMsgEnum;
    }

    @Override
    public String toString() {
        if (null != errorMsgEnum) {
            String format = String.format("%s : %s", errorMsgEnum.getCode(), super.getMessage());
            return format;
        }
        return "";
    }
}
