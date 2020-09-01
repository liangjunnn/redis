package com.lj.redis.common.enums;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author : liang_Jun
 * @ClassName : ErrorMsgEnum
 * @Description :
 * @Date : 2020/3/20 14:37
 */
public enum ErrorMsgEnum {

    /**
     * 接口异常导致服务不可用
     */
    SERVICE_UNAVAILABILITY("100000", "服务不可用"),

    /**
     * 参数异常
     */
    PARAMETER_EXCEPTION("100001", "参数异常"),

    /**
     * 用户不存在
     */
    USER_THERE_IS_NO("100002", "用户不存在"),

    /**
     *用户更新失败
     */
    USER_UPDATE_ERROR("100003","用户更新失败"),

    /**
     * 文件上传失败
     */
    FILE_UPLOAD_ERROR("100004","文件上传失败"),

    /**
     * 文件不存在
     */
    FILE_THERE_IS_NO("100005","文件不存在"),

    /**
     * 文件下载失败
     */
    FILE_DOWNLOAD_ERROR("100006","文件下载失败")

    ;
    /**
     * 错误编码为key的所有错误枚举对象的map集合
     */
    private static Map<String, String> enums = new HashMap<>(ErrorMsgEnum.values().length);

    static {
        for (ErrorMsgEnum e : ErrorMsgEnum.values()) {
            enums.put(e.getCode(), e.getMsg());
        }
    }

    /**
     * 通过错误编码获取错误消息内容
     *
     * @param code 错误编码
     * @return 错误消息内容
     */
    public static String getMsgByCode(String code) {
        return enums.get(code);
    }

    /**
     * 通过错误码枚举，返回一个格式化后的错误信息
     *
     * @param constants 错误码枚举
     * @param args      错误信息格式中需要的参数
     * @return 格式化后的错误信息
     */
    public static String getMsg(ErrorMsgEnum constants, Object... args) {
        return MessageFormat.format(constants.getMsg(), args);
    }

    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误消息提示
     */
    private String msg;

    ErrorMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
