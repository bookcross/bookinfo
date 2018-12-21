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

    private final static String str = "1234567890";
    private final static int pixLen = str.length();
    private static volatile int pixOne = 0;
    private static volatile int pixTwo = 0;
    private static volatile int pixThree = 0;
    private static volatile int pixFour = 0;
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

    //15为
    public static String generate() {
        StringBuilder sb = new StringBuilder();// 创建一个StringBuilder
        sb.append(System.currentTimeMillis());// 先添加当前时间的毫秒值的16进制
        pixFour++;
        if (pixFour == pixLen) {
            pixFour = 0;
            pixThree++;
            if (pixThree == pixLen) {
                pixThree = 0;
                pixTwo++;
                if (pixTwo == pixLen) {
                    pixTwo = 0;
                    pixOne++;
                    if (pixOne == pixLen) {
                        pixOne = 0;
                    }
                }
            }
        }
        return sb.append(str.charAt(pixOne)).append(str.charAt(pixTwo)).append(str.charAt(pixThree)).append(str.charAt(pixFour)).toString();
    }
}
