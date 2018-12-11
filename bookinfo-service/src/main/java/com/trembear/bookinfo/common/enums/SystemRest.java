package com.trembear.bookinfo.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统错误枚举
 *
 * @author wei.wang21 Created by nico on 2018/7/28. 0000：操作成功 1***：管理员系统错误
 * 2***：等等业务系统错误 9***：系统错误 9999：操作失败
 */
public enum SystemRest {

    SUCCESS("0000", "操作成功"),
    CAN_NOT_LEND("1000","该书不能借出"),
    NO_MONEY("1001","没有足够的金币"),

    BBS_NOT_EXISTS("1064", "帖子不存在"),

    BBS_PARAMS_NOT_NULL("1111", "参数不能为空"),

    BBS_CREATE_FAULT("1005", "帖子新增异常"),

    BBS_QUERY_FAULT("1006", "帖子列表查询异常"),

    BBS_UPDATE_FAULT("1067", "帖子更新异常"),

    BASE_NOT_EXISTS("1008", "字典不存在"),

    BASE_COUNT_EXCEPTION("1029", "基础服务统计发生错误"),

    BASE_CREATE_FAULT("1009", "字典新增异常"),

    BASE_QUERY_FAULT("1010", "字典列表查询异常"),

    BASE_UPDATE_FAULT("1011", "字典更新异常"),
    LOGIN_SUCCESS("20000", "登陆成功"),
    UNKNOWN_ERROR("9999", "未知错误");


    private String code;
    private String msg;

     SystemRest(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(String code) {
        for (SystemRest systemException : SystemRest.values()) {
            if (StringUtils.equals(systemException.getCode(), code)) {
                return systemException.getMsg();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
