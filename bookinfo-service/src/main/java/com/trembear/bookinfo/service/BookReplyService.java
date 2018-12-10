package com.trembear.bookinfo.service;

import com.trembear.bookinfo.common.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.entity.BookReply;
import com.trembear.bookinfoapi.dto.BookReplyDto;

/**
 * description 图书回复
 *
 * @author Junwei.Xiong
 * since 2018-12-10 13:32
 */
public interface BookReplyService {
    /**
     * getPageBookReply
     * addBookReply
     * deleteBookReply
     */
    PageDetail<BookReplyDto> getPageBookReplay(int pageNum,int pageSize,long bookId);

    RestFulVO<String> addBookReply(BookReplyDto bookReplyDto);

    RestFulVO<String> deleteBookReply(long id);
}
