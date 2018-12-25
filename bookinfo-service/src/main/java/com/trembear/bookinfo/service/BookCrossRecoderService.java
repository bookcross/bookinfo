package com.trembear.bookinfo.service;

import com.trembear.authorizationapi.dto.UserDto;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfoapi.dto.BookCrossRecoderDto;
import com.trembear.bookinfoapi.dto.BookDto;
import com.trembear.bookinfoapi.vo.PageDetail;

/**
 * description
 *
 * @author Junwei.Xiong
 * since 2018-12-11 14:38
 */
public interface BookCrossRecoderService {
    /**
     * borrowBook
     * acceptBook
     */
    RestFulVO sendNewBook(BookDto bookDto, UserDto userDto);
    RestFulVO borrowBook(BookCrossRecoderDto bookCrossRecoderDto);
    RestFulVO acceptBook(BookCrossRecoderDto bookCrossRecoderDto);
    RestFulVO sendBook(BookCrossRecoderDto bookCrossRecoderDto);
   PageDetail searchByCondition(String type, Integer pageNum, Integer pageSize);
}
