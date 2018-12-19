package com.trembear.bookinfo.service.impl;
import com.alibaba.fastjson.JSON;
import com.trembear.authorizationapi.dto.UserDto;
import com.trembear.bookinfo.BookInfoConst;
import com.trembear.bookinfo.common.vo.BaseRest;
import com.trembear.bookinfoapi.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.dao.BookInfoDao;
import com.trembear.bookinfo.entity.BookInfo;
import com.trembear.bookinfo.service.BookInfoService;
import com.trembear.bookinfo.service.BookReplyService;
import com.trembear.bookinfoapi.dto.BookDto;
import com.trembear.bookinfoapi.dto.PicFileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

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
    private static String CANBRORROW="canLend";
    private static String ISDELETE="isDelete";
    private static String BOOKNAME="bookName";
    private static String AUTHOR="author";
    private static String COLLECT="BOOKCOLLECT_";
    @Autowired
    private BookInfoDao bookInfoDao;
    @Autowired
    private BookReplyService bookReplyService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 调用其他服务示例
     * */
    public void demo(){

    }

    @Override
    public PageDetail<BookDto> getPageDetail(Integer type, Integer pageNum, Integer pageSize) {
        List<BookDto> bookDtos=new ArrayList<>();
        List<BookInfo> list=new ArrayList<>();
        Long total=null;
        Map<String,Object> condition=new HashMap<>();
        condition.put(ISDELETE,BookInfoConst.ISDELETE_FALSE);
        if(type.equals(BookInfoConst.LIST_TYPE_ALL)){
            list=bookInfoDao.pageList(pageNum,pageSize,condition,new Sort(Sort.Direction.DESC,CREATETIME));
            total = bookInfoDao.recordTotal(condition);
        }else if(type.equals(BookInfoConst.LIST_TYPE_CANLEND)){
            condition.put(CANBRORROW,BookInfoConst.CANLEND_TRUE);
            list=bookInfoDao.pageList(pageNum,pageSize,condition,new Sort(Sort.Direction.DESC,CREATETIME));
            total = bookInfoDao.recordTotal(condition);
        }
        /**
         * bookInfo.setLocaion(bookDto.getAddress());
         *         bookInfo.setBookHeadImg(bookDto.getPicList().get(0).getUrl());
         *         bookInfo.setBookPic(JSON.toJSONString(bookDto.getPicList()));
         *         bookInfo.setCanLend("1");
         *         bookInfo.setBookOwner("xxxx");
         *         bookInfo.setCanCrossDate(new Date());
         *         bookInfo.setIsDelete("0");
         *         bookInfo.setType(bookDto.getBookType());
         */

        for(BookInfo bookInfo:list){
            BookDto bookDto=new BookDto();
            BeanUtils.copyProperties(bookInfo,bookDto);
            bookDto.setAddress(bookInfo.getLocaion());
            bookDtos.add(bookDto);
        }
        return new PageDetail<BookDto>(bookDtos, pageNum, pageSize, total);
    }

    @Override
    public PageDetail<BookDto> searchBook(Integer type, String keyword, Integer pageNum, Integer pageSize) {
        List<BookDto> bookDtos=new ArrayList<>();
        List<BookInfo> list=new ArrayList<>();
        Long total=null;
        Map<String,Object> condition=new HashMap<>();
        condition.put(ISDELETE,BookInfoConst.ISDELETE_FALSE);
        if(type.equals(BookInfoConst.SEARCH_TYPE_BOOK)){
            list=bookInfoDao.search(pageNum,pageSize,condition,BOOKNAME, keyword,new Sort(Sort.Direction.DESC,CREATETIME),null);
            total = bookInfoDao.recordTotal(condition);
        }else if(type.equals(BookInfoConst.SEARCH_TYPE_AUTHOR)){
            list=bookInfoDao.search(pageNum,pageSize,condition,AUTHOR, keyword,new Sort(Sort.Direction.DESC,CREATETIME),null);
            total = bookInfoDao.recordTotal(condition);
        }
        for(BookInfo bookInfo:list){
            BookDto bookDto=new BookDto();
            BeanUtils.copyProperties(bookInfo,bookDto);
            bookDtos.add(bookDto);
        }
        return new PageDetail<BookDto>(bookDtos, pageNum, pageSize, total);
    }

    @Override
    public BookDto getBookDetail(Long id) {
        /**
         * TODO
         * 1、获取book信息
         * 2、获取评论信息
         * 3、获取漂流信息
         * 4、从reids中获取是否收藏信息，存入字段
         */
       BookInfo bookInfo= bookInfoDao.findById(id);
       BookDto bookDto=new BookDto();
       BeanUtils.copyProperties(bookInfo,bookDto);
       bookDto.setAddress(bookInfo.getLocaion());
       bookDto.setPicList((List<PicFileDto>) JSON.parseArray(bookInfo.getBookPic(),PicFileDto.class));
       bookDto.setBookType(bookInfo.getType());
       PageDetail pageDetail= bookReplyService.getPageBookReplay(1,5,id,new HashMap());
       bookDto.setBookReplyDtos(pageDetail);

       return bookDto;
    }

    @Override
    public RestFulVO addBook(BookDto bookDto) {
//        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").split("Bearer ")[1];
//        Integer userId=((UserDto)redisTemplate.opsForValue().get(token)).getUserid();
        Integer userId=1;
        BookInfo bookInfo=new BookInfo();
        BeanUtils.copyProperties(bookDto,bookInfo);
        bookInfo.setLocaion(bookDto.getAddress());
        bookInfo.setBookHeadImg(bookDto.getPicList().get(0).getUrl());
        bookInfo.setBookPic(JSON.toJSONString(bookDto.getPicList()));
        bookInfo.setCanLend("1");
        bookInfo.setBookOwner(userId);
        bookInfo.setCanCrossDate(new Date());
        bookInfo.setIsDelete("0");
        bookInfo.setType(bookDto.getBookType());
        bookInfoDao.save(bookInfo);
        return new BaseRest().restSuccess("保存书籍成功");
    }

    @Override
    public RestFulVO deleteBook(Long id) {
        bookInfoDao.delete(id);
        return new BaseRest().restSuccess("删除图书成功");
    }

    @Override
    public RestFulVO collect(Integer bookId, Integer userId, Integer type) {
        //todo
        /**
         *  图书收藏考虑存在redis里，每一个图书都存一个redis的key值。可能有几个问题：
         * 1、key太多了是否会影响性能
         * 2、key太多了到时候如何管理（比如清理删除）
         * 3、需要定时想数据库里统计，避免redis服务停止导致的数据丢失？
         */
        if(type==BookInfoConst.YES) {
            Long result= redisTemplate.opsForSet().add(COLLECT+bookId,userId);
            return new BaseRest().restSuccess("收藏成功");
        }else{
            Long result=redisTemplate.opsForSet().remove(COLLECT+bookId,userId);
            return new BaseRest().restSuccess("取消收藏成功");
        }
    }


    @Override
    public RestFulVO updateBook(BookDto bookDto) {
        BookInfo bookInfo=new BookInfo();
        BeanUtils.copyProperties(bookDto,bookInfo);
        bookInfoDao.update(bookInfo);
        return new BaseRest().restSuccess("更新书籍成功");
    }
}
