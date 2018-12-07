package com.trembear.bookinfo.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * description 适合年龄段
 *
 * @author Junwei.Xiong
 * since 2018-12-07 16:59
 */
public enum  BookSuitbleAge {
    PRESCHOOLER("001","学龄前"),
    CHILD("002","儿童"),
    TEENAGER("003","青年"),
    ADULT("004","成人") ;
    private String code;
    private String msg;
    BookSuitbleAge(String  code, String bookType) {
        this.code=code;
        this.msg=bookType;
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
