package com.trembear.bookinfo.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * description 图书类型枚举
 *
 * @author Junwei.Xiong
 * since 2018-12-07 16:45
 */
public enum  BookType {
    SCHOOL_BOOK("001","教科书"),
    NOVEL("002","小说"),
    COMPUTER_BOOK("003","计算机类"),
    ESSAY("004","散文"),
    POEMS("005","诗歌"),
    LIFE("006","生活"),
    MAGAZINE("007","杂志"),
    PERSON("008","人物");
    private String code;
    private String bookType;
    BookType(String  code, String bookType) {
        this.code=code;
        this.bookType=bookType;
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

    public String getBookType() {
        return bookType;
    }


}
