package com.lj.redis.exception;

import com.lj.redis.common.Result;
import com.lj.redis.common.enums.ErrorMsgEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description: 全局异常
 * @ClassName: GlobalExceptionHandler
 * @Author: liang_jun
 * @Date: 2020/7/23 10:42
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    Result<String> handleCustomException(Exception e) {
        logger.error(e.toString(), e);
        return new Result<String>(ErrorMsgEnum.SERVICE_UNAVAILABILITY.getCode(), ErrorMsgEnum.SERVICE_UNAVAILABILITY.getMsg());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomException.class)
    Result<String> handleCustomException(CustomException e) {
        logger.error(e.toString(), e);
        return new Result<String>(e.getResultStatusEnum().getCode(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    Result<String> handleCustomException(UserException e) {
        logger.error(e.toString(), e);
        return new Result<String>(e.getResultStatusEnum().getCode(), e.getMessage());
    }

}
