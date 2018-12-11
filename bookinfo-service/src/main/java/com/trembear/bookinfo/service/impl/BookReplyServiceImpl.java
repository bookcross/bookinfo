package com.trembear.bookinfo.service.impl;

import com.trembear.bookinfo.BookInfoConst;
import com.trembear.bookinfo.common.vo.BaseRest;
import com.trembear.bookinfo.common.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.dao.BookInfoDao;
import com.trembear.bookinfo.dao.BookReplyDao;
import com.trembear.bookinfo.entity.BookInfo;
import com.trembear.bookinfo.entity.BookReply;
import com.trembear.bookinfo.service.BookReplyService;
import com.trembear.bookinfoapi.dto.BookReplyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Junwei.Xiong
 * since 2018-12-10 13:34
 */
@Service
public class BookReplyServiceImpl implements BookReplyService {
    private static Logger LOGGER=LoggerFactory.getLogger(BookReplyServiceImpl.class);
    private static String ISDELETE = "isDelete";
    private static String CREATETIME = "createTime";
    private static String BOOKID = "bookId";
    private static String PARENT = "parent";
    @Autowired
    private BookReplyDao bookReplyDao;
    @Autowired
    private BookInfoDao bookInfoDao;

    @Override
    public PageDetail<BookReplyDto> getPageBookReplay(int pageNum, int pageSize, long bookId) {
        /**
         * 查找BOOKID下parent为O的前五个书评
         * 查找该书评下的所有回复
         */
        List<BookReplyDto> bookReplyDtos = new ArrayList<>();
        List<BookReply> bookReplies = new ArrayList<>();
        Map<String, Object> condition = new HashMap<>();
        Long total = null;
        condition.put(ISDELETE, BookInfoConst.ISDELETE_FALSE);
        condition.put(BOOKID, bookId);
        condition.put(PARENT, 0);
        bookReplies = bookReplyDao.pageList(pageNum, pageSize, condition, new Sort(Sort.Direction.DESC, CREATETIME));
        total = bookReplyDao.recordTotal(condition);
        for (BookReply bookReply : bookReplies) {
            List<BookReplyDto> bookReplyDtos2 = new ArrayList<>();
            BookReplyDto bookReplyDto = new BookReplyDto();
            BeanUtils.copyProperties(bookReply, bookReplyDto);
            condition.put(PARENT, bookReply.getId());
            List<BookReply> bookCommentReplies = bookReplyDao.findByCondition(condition);
            for (BookReply bookReply1 : bookCommentReplies) {
                BookReplyDto bookReplyDto3 = new BookReplyDto();
                BeanUtils.copyProperties(bookReply1, bookReplyDto3);
                bookReplyDtos2.add(bookReplyDto3);
            }
            bookReplyDto.setBookReplyDtos(bookReplyDtos2);
            bookReplyDtos.add(bookReplyDto);
        }
        return new PageDetail<BookReplyDto>(bookReplyDtos, pageNum, pageSize, total);
    }

    @Override
    public RestFulVO<String> addBookReply(BookReplyDto bookReplyDto) {
        BookReply bookReply = new BookReply();
        BeanUtils.copyProperties(bookReplyDto, bookReply);
        bookReplyDao.save(bookReply);
        if(!addStar(bookReplyDto.getStar(),bookReplyDto.getBookId())){
            LOGGER.error("存入分数发生一场");
        };
        return new BaseRest().restSuccess("保存评论成功");
    }

    @Override
    public RestFulVO<String> deleteBookReply(long id) {
        bookReplyDao.delete(id);
        return new BaseRest().restSuccess("保存评论成功");
    }

    @Override
    public boolean addStar(float star, long bookId) {
        try {
            BookInfo bi = bookInfoDao.findById(bookId);
            bi.setStar(bi.getStar() + star);
            bi.setStarNum(bi.getStarNum() + 1);
            bookInfoDao.update(bi);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
