package com.trembear.bookinfo.service.impl;

import com.trembear.bookinfo.BookInfoConst;
import com.trembear.bookinfo.common.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.dao.BookInfoDao;
import com.trembear.bookinfo.entity.BookInfo;
import com.trembear.bookinfo.service.BookInfoService;
import com.trembear.bookinfoapi.dto.BookDetailDto;
import com.trembear.bookinfoapi.dto.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author Junwei.Xiong
 * @description
 * @since 2018-12-06 16:36
 */

@Service
public class BookInfoServiceImpl
        implements BookInfoService{
    private static final Logger LOGGER = LoggerFactory.getLogger(BookInfoServiceImpl.class);
    private static String CREATETIME="createTime";
    private static String CANBRORROW="canBorrow";
    private static String ISDELETE="isDelete";
    @Autowired
    private BookInfoDao bookInfoDao;

    /**
     * 调用其他服务示例
     * */
    public void demo(){

    }

    @Override
    public PageDetail<BookDto> getPageDetail(Integer type, Integer pageNum, Integer pageSize) {
        List<BookInfo> list=new ArrayList<>();
        Map<String,Object> condition=new HashMap<>();
        condition.put(ISDELETE,BookInfoConst.ISDELETE_FALSE);
        if(type.equals(BookInfoConst.SERARCH_TYPE_ALL)){
            list=bookInfoDao.pageList(pageNum,pageSize,condition,new Sort(Sort.Direction.DESC,CREATETIME));
        }else if(type.equals(BookInfoConst.SERARCH_TYPE_CANLEND)){
            condition.put(CANBRORROW,BookInfoConst.CANLEND_TRUE);
            list=bookInfoDao.pageList(pageNum,pageSize,condition,new Sort(Sort.Direction.DESC,CREATETIME));
        }
        return null;
    }

    @Override
    public PageDetail<BookDto> searchBook(String field, String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public BookDetailDto getBookDetail(Integer id) {
        return null;
    }

    @Override
    public RestFulVO<String> addBook(BookDto bookDto) {
        return null;
    }

    @Override
    public RestFulVO<String> deleteBook(Integer id) {
        return null;
    }

    @Override
    public RestFulVO<String> collect(Integer bookId, Integer userId, Integer type) {
        return null;
    }

    @Override
    public RestFulVO<String> updateBook(BookDto bookDto) {
        return null;
    }
}
