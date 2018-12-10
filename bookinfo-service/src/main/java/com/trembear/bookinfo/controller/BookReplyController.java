package com.trembear.bookinfo.controller;

import com.trembear.bookinfo.common.vo.PageDetail;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.service.BookReplyService;
import com.trembear.bookinfoapi.dto.BookReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description 书评
 *
 * @author Junwei.Xiong
 * since 2018-12-10 11:21
 */
@RestController
@RequestMapping("/bookReply")
public class BookReplyController {
    @Autowired
    BookReplyService bookReplyService;
    /**
     * @author junwei.xiong
     * Description  //TODO 分页获取书评前五条（包含书评的回复）
     * @date 11:24 2018/12/10
     * @param []
     * @return com.trembear.bookinfo.common.vo.PageDetail
     **/
    @RequestMapping("/readAll")
    public PageDetail getPageBookReply(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "bookId") long bookId){
        return bookReplyService.getPageBookReplay(pageNum,pageSize,bookId);
    }
    /**
     * @author junwei.xiong
     * Description  //TODO addBookReply
     * @date 14:51 2018/12/10
     * @param [bookReplyDto]
     * @return com.trembear.bookinfo.common.vo.RestFulVO
     **/
    @RequestMapping("/saveReply")
    public RestFulVO addBookReply(@RequestBody BookReplyDto bookReplyDto){
        return bookReplyService.addBookReply(bookReplyDto);
    }
    /**
     * @author junwei.xiong
     * Description  //TODO deleteBookReply
     * @date 14:51 2018/12/10
     * @param [id]
     * @return com.trembear.bookinfo.common.vo.RestFulVO
     **/
    @RequestMapping("/deleteBookReply")
    public RestFulVO deleteBookReply(@RequestParam(value = "id")long id){
        return bookReplyService.deleteBookReply(id);
    }





}
