package com.trembear.bookinfo.controller;

import com.trembear.bookinfo.common.vo.RestFulVO;

/**
 * description 图书漂流情况
 *
 * @author Junwei.Xiong
 * since 2018-12-11 14:04
 */
public class BookCrossController {
    /**
     * 发起借阅图书（增加一条图书交易记录，并扣除一方一个金币，系统金币池增加一个金币）
     * 收到图书（增加一条图书收到的记录，并扣除一方一个金币，金币池扣一个金币，另一方增加二个金币）
     */
    public RestFulVO borrowBook(){
        return null;
    }
    public RestFulVO acceptBook(){
        return null;
    }
}
