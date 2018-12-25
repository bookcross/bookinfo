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
import com.trembear.bookinfoapi.vo.PageDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    public RestFulVO   borrowBook(BookCrossRecoderDto bookCrossRecoderDto){
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
                    String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").split("Bearer ")[1];
                    String userSerilize = (String) redisTemplate.opsForValue().get(token);
                    UserDto userDto = JSON.parseObject(userSerilize, UserDto.class);
                    BeanUtils.copyProperties(bookCrossRecoderDto,bookCrossRecoder);
                    /**
                     *  Integer senderId;
                     *  String senderName;
                     *  String  senderJ;
                     *  String  senderW;
                     *  String senderAddress;
                     *  Integer accepterId;
                     *  String accepterName;
                     *  String accepterJ;
                     *  String accepterW;
                     *  String accepterAddress;
                     *  String message;
                     *  Date sendTime;
                     *  Date acceptTime;
                     */
                    bookCrossRecoder.setSenderId(bookInfo.getBookNow());
                    bookCrossRecoder.setSenderName(bookInfo.getBookNowName());
                    bookCrossRecoder.setSenderJ(bookInfo.getAddressJ());
                    bookCrossRecoder.setSenderW(bookInfo.getAddressW());
                    bookCrossRecoder.setSenderAddress(bookInfo.getLocaion());
                    bookCrossRecoder.setAccepterId(userDto.getUserid());
                    bookCrossRecoder.setAccepterName(userDto.getUsername());
                    bookCrossRecoder.setType("1");
                    bookCrossRecoder.setCreateTime(new Date());
                    bookCrossRecoderDao.save(bookCrossRecoder);
                    cal.set(Calendar.DATE,cal.get(Calendar.DATE)+50);
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

    /**
     * 点击收书按钮
     * @param bookCrossRecoderDto
     * @return
     */
    @Override
    public RestFulVO acceptBook(BookCrossRecoderDto bookCrossRecoderDto){
        BookCrossRecoder bookCrossRecoder=bookCrossRecoderDao.findById(bookCrossRecoderDto.getId());
        bookCrossRecoder.setIsAccept("1");
        bookCrossRecoder.setAcceptTime(new Date());
        bookCrossRecoderDao.update(bookCrossRecoder);
        BookCoinRecoder bookCoinRecoder=new BookCoinRecoder();
        bookCoinRecoder.setUserId(bookCrossRecoder.getAccepterId());
        bookCoinRecoder.setType(BookInfoConst.ISTRADE_TRUE);
        bookCoinRecoder.setCrossRecoderId(bookCrossRecoder.getId());
        bookCoinRecoder.setCreateTime(new Date());
        bookCoinRecoder.setCoin(2);
        bookCoinRecoderDao.save(bookCoinRecoder);
        //个人金币加2
        return new RestFulVO(SystemRest.SUCCESS);
    }
    /**
     * 发送书籍按钮
     */
    @Override
    public RestFulVO sendBook(BookCrossRecoderDto bookCrossRecoderDto){
        BookCrossRecoder bcr=new BookCrossRecoder();
        BeanUtils.copyProperties(bookCrossRecoderDto,bcr);
        bcr.setIsSend("1");
        bcr.setSendTime(new Date());
        bookCrossRecoderDao.update(bcr);
        return new RestFulVO(SystemRest.SUCCESS);
    }

    /**
     * 新书发布
     */
    @Override
    public RestFulVO sendNewBook(BookDto bookDto, UserDto userDto){
        BookCrossRecoder bookCrossRecoder=new BookCrossRecoder();
        bookCrossRecoder.setIsAccept("1");
        bookCrossRecoder.setSendTime(new Date());
        bookCrossRecoder.setIsSend("1");
        bookCrossRecoder.setBookId(1L);
        //设置为第一次发布
        bookCrossRecoder.setType("0");
        bookCrossRecoder.setAccepterId(userDto.getUserid());
        bookCrossRecoder.setName(bookDto.getBookName());
        bookCrossRecoder.setAccepterAddress(bookDto.getAddress());
        bookCrossRecoder.setAccepterName(userDto.getUsername());
        bookCrossRecoder.setAccepterJ(bookDto.getAddressJ());
        bookCrossRecoder.setAccepterW(bookDto.getAddressW());
        bookCrossRecoderDao.save(bookCrossRecoder);
        return new BaseRest().restSuccess("success");
    }

    /**
     * 查询所有
     */
    @Override
    public PageDetail searchByCondition(String type, Integer pageNum, Integer pageSize){
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").split("Bearer ")[1];
        String userSerilize = (String) redisTemplate.opsForValue().get(token);
        UserDto userDto = JSON.parseObject(userSerilize, UserDto.class);
        Map<String, Object> condition = new HashMap<>();
        List<BookCrossRecoder> bookCrossRecoders=null;
        condition.put("type","1");
        //查询
        if(type.equals("0")){
            condition.put("senderId", userDto.getUserid());
            bookCrossRecoders=bookCrossRecoderDao.findByCondition(condition,new Sort(Sort.Direction.DESC, "isSend"));
        }else {
            condition.put("accepterId", userDto.getUserid());
            bookCrossRecoders=bookCrossRecoderDao.findByCondition(condition,new Sort(Sort.Direction.DESC, "isAccept"));
        }
        List<BookCrossRecoderDto> bookCrossRecoderDtos=new ArrayList<>();
        if (bookCrossRecoders != null && bookCrossRecoders.size() > 0) {
            for (BookCrossRecoder bookCrossRecoder : bookCrossRecoders) {
                BookCrossRecoderDto bookCrossRecoderDto = new BookCrossRecoderDto();
                BeanUtils.copyProperties(bookCrossRecoder, bookCrossRecoderDto);
                bookCrossRecoderDtos.add(bookCrossRecoderDto);
            }
        }
        Long total = bookCrossRecoderDao.recordTotal(condition);
        return new PageDetail<BookCrossRecoderDto>(bookCrossRecoderDtos, pageNum, pageSize, total);

    }
}
