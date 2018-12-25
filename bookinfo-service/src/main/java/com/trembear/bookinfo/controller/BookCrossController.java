package com.trembear.bookinfo.controller;

import com.github.pagehelper.Page;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfo.service.BookCrossRecoderService;
import com.trembear.bookinfo.service.BookInfoService;
import com.trembear.bookinfoapi.dto.BookCrossRecoderDto;
import com.trembear.bookinfoapi.dto.BookDto;
import com.trembear.bookinfoapi.vo.PageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description 图书漂流情况
 *
 * @author Junwei.Xiong
 * since 2018-12-11 14:04
 */
@RestController
@RequestMapping("/bookCross")
public class BookCrossController {
    @Autowired
    BookInfoService bookInfoService;
    @Autowired
    BookCrossRecoderService bookCrossRecoderService;
    /**
     * 发起借阅图书（增加一条图书交易记录，并扣除一方一个金币，系统金币池增加一个金币）
     * 收到图书（增加一条图书收到的记录，并扣除一方一个金币，金币池扣一个金币，另一方增加二个金币）
     */
    @RequestMapping("/borrowBook")
    public RestFulVO borrowBook(@RequestBody BookCrossRecoderDto bookCrossRecoderDto){
        return bookCrossRecoderService.borrowBook(bookCrossRecoderDto);
    }

    /**
     * 接收图书
     * @return
     */
    @RequestMapping("/acceptBook")
    public RestFulVO acceptBook(@RequestBody BookCrossRecoderDto bookCrossRecoderDto){
        return bookCrossRecoderService.acceptBook(bookCrossRecoderDto);
    }

    /**
     * 发送图书
     * @return
     */
    @RequestMapping("/sendBook")
    public RestFulVO sendBook(@RequestBody BookCrossRecoderDto bookCrossRecoderDto){
        return bookCrossRecoderService.sendBook(bookCrossRecoderDto);
    }

    @RequestMapping("/searchCross")
    public PageDetail searchCross(@RequestParam(value = "type") String type,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return bookCrossRecoderService.searchByCondition(type,pageNum,pageSize);
    }

}
