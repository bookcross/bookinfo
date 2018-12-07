package com.trembear.bookinfoapi.client;
import com.trembear.bookinfoapi.BookConst;
/**
 * description 此服务对外暴露的接口
 *              FeignClient第一个参数为ServiceName,第二个参数为熔断的地址
 *              如果有其他服务调用这个service，首先再那个服务上Application中扫描引入的client包，
 *              载入BookClient，即可调用里面的方法。
 * @author Junwei.Xiong
 * since 2018-12-07 15:36
 */

//@FeignClient(value = BookConst.BBSCOMMON_SERVICE_NAME, fallbackFactory = BulletinClientFallbackFactory.class)
public class BookClient {
}
