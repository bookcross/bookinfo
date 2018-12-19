package com.trembear.bookinfo.service;

import com.trembear.bookinfoapi.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfoapi.dto.BookReplyDto;

import java.util.Map;

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
    PageDetail<BookReplyDto> getPageBookReplay(int pageNum,int pageSize,Long bookId,Map<String,Object> map);

    RestFulVO<String> addBookReply(BookReplyDto bookReplyDto);

    RestFulVO<String> deleteBookReply(Long id);

    boolean addStar(float star,Long bookId);
}
