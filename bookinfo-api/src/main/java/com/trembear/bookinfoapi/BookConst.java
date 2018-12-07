package com.trembear.bookinfoapi;

/**
 * @author Junwei.Xiong
 * @description 提供调用的公用类
 * @since 2018-09-12 15:52
 */
public class BookConst {
    /**
     * 服务名称
     */
    public static final String BBSCOMMON_SERVICE_NAME = "bbscommon-service";

    /**
     * feign client 包扫描路径
     */
    public static final String BBSCOMMON_CLIENT_SCAN_PATH = "com.ucarbbs.commonclient.service";

    /**
     * feign client 熔断器扫描路径
     */
    public static final String BBSCOMMON_CLIENT_HYSTRIX_SCAN_PATH = "com.ucarbbs.commonclient.hystrix";
}
