package com.trembear.bookinfo.common.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description
 *
 * @author Junwei.Xiong
 * since 2018-12-11 17:34
 */
public class IDUtils {
    private static byte[] lock = new byte[0];

    // 位数，默认是8位
    private final static Long w = 100000000L;
    private static SecureRandom random = new SecureRandom();

    public static String createID() {
        Long r = 0L;
        synchronized (lock) {
            r = (long) ((random.nextFloat() + 1) * w);
        }

        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }

    public static String createData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

}
