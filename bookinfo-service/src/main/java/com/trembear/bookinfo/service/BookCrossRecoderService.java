package com.trembear.bookinfo.service;

import com.trembear.authorizationapi.dto.UserDto;
import com.trembear.bookinfo.common.vo.RestFulVO;
import com.trembear.bookinfoapi.dto.BookDto;

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

}
