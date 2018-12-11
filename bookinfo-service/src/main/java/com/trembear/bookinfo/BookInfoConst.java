package com.trembear.bookinfo;

/**
 * description 这个系统中的常量池
 *
 * @author Junwei.Xiong
 * since 2018-12-07 16:43
 */
public class BookInfoConst {
    public static String CANLEND_TRUE="1";//可借
    public static String CANLEND_FALSE="0";//不可借
    public static String ISDELETE_TRUE="1";//已删除
    public static String ISDELETE_FALSE="0";//未删除
    public static String ISTRADE_TRUE="1";//未删除
    public static String ISTRADE_FALSE="0";//未删除
    public static int LIST_TYPE_ALL=0;//查询所有
    public static int LIST_TYPE_CANLEND=1;//查询可借
    public static int SEARCH_TYPE_BOOK=0;//根据书名查询
    public static int SEARCH_TYPE_AUTHOR=1;//根据作者查询
    //通用
    public static int YES=1;//收藏
    public static int NO=0;//取消收藏

}
