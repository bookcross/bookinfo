package com.trembear.bookinfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.trembear.authorizationapi.dto.UserDto;
import com.trembear.bookinfo.BookInfoConst;
import com.trembear.bookinfo.common.enums.SystemRest;
import com.trembear.bookinfo.common.vo.BaseRest;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.dao.BookCoinRecoderDao;
import com.trembear.bookinfo.dao.BookCrossRecoderDao;
import com.trembear.bookinfo.dao.BookInfoDao;
import com.trembear.bookinfo.entity.BookCoinRecoder;
import com.trembear.bookinfo.entity.BookCrossRecoder;
import com.trembear.bookinfo.entity.BookInfo;
import com.trembear.bookinfo.service.BookCrossRecoderService;
import com.trembear.bookinfoapi.dto.BookCrossRecoderDto;
import com.trembear.bookinfoapi.dto.BookDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description
 *
 * @author Junwei.Xiong
 * since 2018-12-11 14:38
 */
@Service
public class BookCrossRecoderServiceImpl implements BookCrossRecoderService {
    @Autowired
    BookInfoDao bookInfoDao;
    @Autowired
    BookCrossRecoderDao bookCrossRecoderDao;
    @Autowired
    BookCoinRecoderDao bookCoinRecoderDao;
    @Autowired
    RedisTemplate redisTemplate;
    /**
     * borrowBook：申请借书
     */
    public RestFulVO borrowBook(BookCrossRecoderDto bookCrossRecoderDto){
        /**
         * 1 判断是否可借（图书是否在可借期限内，金币是否足够两个）
         * 2 增加一条记录，
         */
        BookCrossRecoder bookCrossRecoder=new BookCrossRecoder();
        BookCoinRecoder bookCoinRecoder=new BookCoinRecoder();
        BookInfo bookInfo=bookInfoDao.findById(bookCrossRecoderDto.getBookId());
        Calendar cal = Calendar.getInstance();
        if(bookInfo.getCanLend().equals(BookInfoConst.CANLEND_TRUE)){
            /**判断金币数量是否足够*/
            if(true){
                try {
                    BeanUtils.copyProperties(bookCrossRecoderDto,bookCrossRecoder);
                    bookCrossRecoder.setAccept(true);
                    bookCrossRecoder.setSendTime(new Date());
                    bookCrossRecoderDao.save(bookCrossRecoder);
                    cal.set(Calendar.DATE,cal.get(Calendar.DATE)+30);
                    bookInfo.setCanCrossDate(cal.getTime());
                    bookInfo.setCanLend(BookInfoConst.CANLEND_FALSE);
                    bookInfoDao.save(bookInfo);
                    bookCoinRecoder.setCoin(-1);
                    bookCoinRecoder.setCreateTime(new Date());
                    bookCoinRecoder.setCrossRecoderId(bookCrossRecoderDto.getId());
                    bookCoinRecoder.setType(BookInfoConst.ISTRADE_TRUE);
                    bookCoinRecoder.setUserId(bookCrossRecoderDto.getSenderId());
                    bookCoinRecoderDao.save(bookCoinRecoder);
                    return new RestFulVO(SystemRest.SUCCESS);
                }catch (Exception e){
                    return new RestFulVO(SystemRest.UNKNOWN_ERROR);
                }

            }else{
                return  new RestFulVO(SystemRest.NO_MONEY);
            }
        }else{
            return new RestFulVO(SystemRest.CAN_NOT_LEND);
        }

    }

    public RestFulVO acceptBook(BookCrossRecoderDto bookCrossRecoderDto){
        BookCrossRecoder bookCrossRecoder=bookCrossRecoderDao.findById(bookCrossRecoderDto.getId());
        bookCrossRecoder.setAccept(true);
        bookCrossRecoder.setAcceptTime(new Date());
        bookCrossRecoderDao.update(bookCrossRecoder);
        BookCoinRecoder bookCoinRecoder=new BookCoinRecoder();
        bookCoinRecoder.setUserId(bookCrossRecoder.getAccepterId());
        bookCoinRecoder.setType(BookInfoConst.ISTRADE_TRUE);
        bookCoinRecoder.setCrossRecoderId(bookCrossRecoder.getId());
        bookCoinRecoder.setCreateTime(new Date());
        bookCoinRecoder.setCoin(2);
        bookCoinRecoderDao.save(bookCoinRecoder);
        return new RestFulVO(SystemRest.CAN_NOT_LEND);
    }

    /**
     * 新书发布
     */
    public RestFulVO sendNewBook(BookDto bookDto, BookCrossRecoderDto bookCrossRecoderDto){
        BookCrossRecoder bookCrossRecoder=new BookCrossRecoder();
        bookCrossRecoder.setAccept(true);
        bookCrossRecoder.setSendTime(new Date());
        bookCrossRecoder.setSend(true);
        bookCrossRecoder.setBookId(1L);
        //设置为第一次发布
        bookCrossRecoder.setType("0");
        bookCrossRecoder.setAccepterId(bookDto.getBookOwner());
        bookCrossRecoder.setName(bookDto.getBookName());
        bookCrossRecoder.setAccepterAddress(bookDto.getAddress());
        bookCrossRecoder.setAccepterJ(bookDto.getAddressJ());
        bookCrossRecoder.setAccepterW(bookDto.getAddressW());
        bookCrossRecoderDao.save(bookCrossRecoder);
        return new BaseRest().restSuccess("success");
    }
}
